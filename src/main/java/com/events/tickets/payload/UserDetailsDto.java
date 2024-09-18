package com.events.tickets.payload;

import java.util.Set;

public class UserDetailsDto {
	private String username;
    private Set<RoleDto> roles;
    public UserDetailsDto(String name, String username, String surname, String email, Set<RoleDto> roles) {
        this.username = username;
        this.roles = roles;
    }
 public UserDetailsDto() {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Set<RoleDto> getRoles() {
        return roles;
    }
    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    

}
