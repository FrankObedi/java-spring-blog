package org.frankobedi.SpringBlog.util.constants;

public enum Roles {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), EDITOR("ROLE_EDITOR");

    private final String role; // Field to store user role

    // Constructor to initialize the enum constant
    private Roles(String role){
        this.role = role;
    }

    // Method to retrieve user role
    public String getRole(){
        return role;
    }
}
