package org.example.techmateaccessories.domain.dto;

import jakarta.validation.constraints.NotBlank;

public class InfoUser {
    @NotBlank(message = "Name is required")
    private String name ;
    @NotBlank(message = "Address is required")
    private String address ;
    @NotBlank(message = "Phone is required")
    private String phone ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
