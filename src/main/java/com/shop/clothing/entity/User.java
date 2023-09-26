package com.shop.clothing.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User  {

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


    @ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "normalized_name")
    )
    private java.util.List<Role> roles;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private java.util.List<Order> orders;

}
