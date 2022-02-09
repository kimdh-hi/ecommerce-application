package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

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
        model.addAttribute("pageTitle", "사용자 추가");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        if (Objects.isNull(user.getId())) {
            userService.save(user);
            redirectAttributes.addFlashAttribute("message", "사용자 추가 완료");
        }
        else {
            userService.updateUser(user);
            redirectAttributes.addFlashAttribute("message", "사용자 정보변경 완료");
        }

        return "redirect:/users";
    }

    @GetMapping("/users/edit/{userId}")
    public String editUser(@PathVariable Long userId, RedirectAttributes redirectAttributes, Model model) {
        try {
            User user = userService.findUser(userId);
            List<Role> roles = userService.findRoles();
            model.addAttribute("user", user);
            model.addAttribute("roles", roles);
            model.addAttribute("pageTitle", "사용자 정보수정");
            return "user_form";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }
}
