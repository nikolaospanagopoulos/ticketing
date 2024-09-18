package com.events.tickets.payload;

public class RoleDto {
    private long id;
    private String name;
    public RoleDto(long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public RoleDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
