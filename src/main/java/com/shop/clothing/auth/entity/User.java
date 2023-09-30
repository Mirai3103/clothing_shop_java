package com.shop.clothing.auth.entity;

import com.shop.clothing.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(nullable = false,length = 100)
    private  String firstName;


    @Column(nullable = false,length = 100)
    private String lastName;

    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @Column(nullable = false,length = 100)
    private String passwordHash;

    @Column(length = 100)
    private String phoneNumber;

    @Column(length = 500)
    private String address;

    @Column(length = 200)
    private String avatarUrl;


    private boolean isEmailVerified=false;


    private boolean isAccountEnabled=true;


    private boolean isCustomer = true;

    private Date createdAt = new Date();


    @ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "normalized_name")
    )
    private java.util.List<Role> roles;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private java.util.List<Order> orders;

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
        return getGrantedAuthorities(getPermissions(this.getRoles()));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isAccountEnabled;
    }
}
