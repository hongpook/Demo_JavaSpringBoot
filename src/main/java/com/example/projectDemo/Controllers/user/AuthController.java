package com.example.projectDemo.Controllers.user;

import com.example.projectDemo.Dto.RegisterDto;
import com.example.projectDemo.Entity.User;
import com.example.projectDemo.Services.CustomUserDetailsService;
import com.example.projectDemo.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/home")
    public String getHomePage(Model model){
        model.addAttribute("user");
        return "user/pages/home";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginUser", new RegisterDto());
        return "user/auth/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDto());
        return "user/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("getRegisterPage") RegisterDto registerDTO) {
        User user = userService.registerDtoUser(registerDTO);
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(userService.getRoleByName("user"));

        userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String performLogout() {
        return "redirect:/home";
    }


    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }


    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("email", userDetails.getUsername());
            model.addAttribute("roles", userDetails.getAuthorities());
            model.addAttribute("username");
            model.addAttribute("address" );
        } else {
            return "redirect:/login";
        }
        return "user/pages/profile";
    }

}
