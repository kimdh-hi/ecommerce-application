package com.shopme.admin.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/check-email")
    public String checkDuplicateEmail(String email) {
        return userService.isUniqueEmail(email) ? "Ok" : "Duplicated";
    }
}
