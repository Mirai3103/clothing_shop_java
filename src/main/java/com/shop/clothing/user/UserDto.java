package com.shop.clothing.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserDto {

    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private String avatarUrl;

    private boolean isEmailVerified = false;

    private boolean isAccountEnabled = true;
    private boolean isCustomer = true;
    private Date createdAt = new Date();
    private Collection<String> permissions;

    public String toJsonObject() {
        return "{" +
                "\"userId\":\"" + userId + '\"' +
                ", \"firstName\":\"" + firstName + '\"' +
                ", \"lastName\":\"" + lastName + '\"' +
                ", \"email\":\"" + email + '\"' +
                ", \"phoneNumber\":\"" + phoneNumber + '\"' +
                ", \"address\":\"" + address + '\"' +
                ", \"avatarUrl\":\"" + avatarUrl + '\"' +
                ", \"isEmailVerified\":" + isEmailVerified +
                ", \"isAccountEnabled\":" + isAccountEnabled +
                ", \"isCustomer\":" + isCustomer +
                '}';
    }
}
