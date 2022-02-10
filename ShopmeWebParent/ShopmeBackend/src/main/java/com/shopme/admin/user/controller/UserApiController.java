package com.shopme.admin.user.controller;

import com.shopme.admin.user.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/check-email")
    public String checkDuplicateEmail(@Param("usreId") Long userId, @Param("email") String email) {
        return userService.isUniqueEmail(userId, email) ? "Ok" : "Duplicated";
    }
}
