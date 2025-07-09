package com.arthur.schedulingApi.models.user;

public enum UserRoles {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    MANAGER_VIP("MANAGER_VIP"),
    USER("USER");

    private final String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
