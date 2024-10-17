package com.example.projectDemo.Controllers.admin;

import com.example.projectDemo.Entity.User;
import com.example.projectDemo.Repositories.UserRepository;
import com.example.projectDemo.Services.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }



    @GetMapping("admin/")
    public String getDashboard(){
        return "admin/dashboard/dashboard";
    }

    @GetMapping("/admin/user")
    public String usersList(Model model, @Param("keyword") String keyword){
        List<User> users;

        if (keyword != null && !keyword.trim().isEmpty()) {
            users = this.userService.searchUser(keyword);
        } else {
            users = this.userService.getAllUsers();
        }
        model.addAttribute("allUsers", users);
        return "admin/user/usersList";
    }

    @GetMapping("/admin/user/{id}")
    public String detailUser(Model model, @PathVariable long id){
        User users = this.userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", users);
        return "admin/user/viewUser";
    }

    @GetMapping("/admin/user/create")
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "admin/user/createUser";
    }

    @PostMapping("/admin/user/create")
    public String saveUser(@ModelAttribute("user") User person, Model model) {

        String hashPassword = this.passwordEncoder.encode(person.getPassword());
        person.setPassword(hashPassword);
        person.setRole(this.userService.getRoleByName(person.getRole().getName()));
        this.userService.handleSaveUser(person);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/update/{id}")
    public String updateUser(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("user", currentUser);
        return "admin/user/updateUser";
    }

    @PostMapping("/admin/user/update")
    public String updateUser(Model model, @ModelAttribute("user") User person) {
        User currentUser = this.userService.getUserById(person.getId());
        if (currentUser != null) {
            currentUser.setAddress(person.getAddress());
            currentUser.setPhone(person.getPhone());
            currentUser.setEmail(person.getEmail());
            currentUser.setUsername(person.getUsername());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }


    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("user", currentUser);
        return "admin/user/deleteUser";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(@RequestParam("id") long id) {
        this.userService.deleteUserById(id);
        return "redirect:/admin/user";
    }

}
