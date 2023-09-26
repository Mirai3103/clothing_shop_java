package com.shop.clothing.security;

import com.shop.clothing.entity.Permission;
import com.shop.clothing.entity.Role;
import com.shop.clothing.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Getter
public class MyUserDetail implements UserDetails {
    private String id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    private String phone;
    private String address;


    private List<GrantedAuthority> authorities;
    private boolean isConfirmedEmail;
    private boolean isEnable;
    public MyUserDetail(User user){
        this.id = user.getUserId();
        this.username = user.getEmail();
        this.password = user.getPasswordHash();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.avatar = user.getAvatarUrl();
        this.phone = user.getPhoneNumber();
        this.address = user.getAddress();
        this.isConfirmedEmail = user.isEmailVerified();
        this.isEnable = user.isAccountEnabled();
        this.authorities = getGrantedAuthorities(getPermissions(user.getRoles()));

    }
    private List<String> getPermissions(Collection<Role> roles) {

        List<String> permissions = new ArrayList<>();
        List<Permission> collection = new ArrayList<>();
        for (Role role : roles) {
            permissions.add(role.getNormalizedName());
            collection.addAll(role.getPermissions());
        }
        for (Permission item : collection) {
            permissions.add(item.getNormalizedName());
        }
        return permissions;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnable;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnable;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnable;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }
}
