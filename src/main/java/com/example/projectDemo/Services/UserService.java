package com.example.projectDemo.Services;

import com.example.projectDemo.Dto.RegisterDto;
import com.example.projectDemo.Entity.Role;
import com.example.projectDemo.Entity.User;
import com.example.projectDemo.Repositories.RoleRepository;
import com.example.projectDemo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserService(RoleRepository roleRepository, UserRepository userRepository){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(long id){
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User deleteUserById(long id){
        return userRepository.deleteById(id);
    }

    public Role getRoleByName(String name){
        return roleRepository.findByName(name);
    }

    public User registerDtoUser(RegisterDto registerDto){
        User user = new User();

        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhone());
        user.setPassword(registerDto.getPassword());
        return user;
    }

    public User handleSaveUser(User user){
        User person = userRepository.save(user);
        return person;
    }

    public List<User> searchUser(String keyword){
        return this.userRepository.search(keyword);
    }

}
