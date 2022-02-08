package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        List<Role> roles = userService.findRoles();
        User user = new User();

        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
        userService.save(user);
        return "redirect:/users";
    }

}
