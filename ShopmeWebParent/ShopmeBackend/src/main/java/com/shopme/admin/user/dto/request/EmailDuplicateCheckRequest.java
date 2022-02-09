package com.shopme.admin.user.dto.request;

public class EmailDuplicateCheckRequest {

    private String email;
    private Long userId;

    public EmailDuplicateCheckRequest() {
    }

    public EmailDuplicateCheckRequest(String email, Long userId) {
        this.email = email;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
