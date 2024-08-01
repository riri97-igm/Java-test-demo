package team6.java.ca.entities;

import jakarta.validation.constraints.Size;

public class LoginDTO {
    @Size(min=2, max=20, message="Username must be 2-20 characters")
    private String username;

    @Size(min=6, max=30, message="Password must be 6-30 characters")
    private String password;
    
    
    public LoginDTO()
    {
    	
    }
    
    public LoginDTO(String username, String password)
    {
    	this.username = username;
    	this.password = password;
    }
    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

