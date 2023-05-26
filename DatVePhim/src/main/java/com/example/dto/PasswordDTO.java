package com.example.dto;

import jakarta.validation.constraints.NotEmpty;

public class PasswordDTO {

    @NotEmpty(message = "Mật khẩu hiện tại không được để trống")
    private String currentPassword;

    @NotEmpty(message = "Mật khẩu mới không được để trống")
    private String newPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}