package com.shopme.admin.user;

public class UserNotFoundException extends RuntimeException {

    public static String NOT_FOUND_MESSAGE = "사용자를 찾을 수 없습니다.";

    public UserNotFoundException(String message) {
        super(message);
    }
}
