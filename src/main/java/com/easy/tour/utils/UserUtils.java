package com.easy.tour.utils;

import com.easy.tour.Enum.RoleName;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

@Component
public class UserUtils {
    public String generatePassword() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }
        return password.toString();
    }

    public byte setRoleForUser(String role) {
        if (RoleName.ROLE_USER.equals(role)) {
            return RoleName.USER.getValue();
        } else if (RoleName.ROLE_MANAGER.equals(role)) {
            return RoleName.MANAGER.getValue();
        } else if (RoleName.ADMIN.equals(role)) {
            return RoleName.ADMIN.getValue();
        } else {
            throw new IllegalArgumentException("Not Found Role!");
        }
    }

    public String getRoleForUser(byte role) {
        if (RoleName.USER.equals(role)) {
            return RoleName.ROLE_USER;
        } else if (RoleName.MANAGER.equals(role)) {
            return RoleName.ROLE_MANAGER;
        } else if (RoleName.ADMIN.equals(role)) {
            return RoleName.ROLE_ADMIN;
        } else {
            throw new IllegalArgumentException("Not Found Role!");
        }
    }
}
