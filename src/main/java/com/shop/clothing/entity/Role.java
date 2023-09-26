package com.shop.clothing.entity;

import com.shop.clothing.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.List;
import org.hibernate.mapping.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table
public class Role extends AuditableEntity {
    @Id
    @Column(name = "normalized_name", length = 50,updatable = false)
    private String normalizedName;

    @Column(name = "display_name", length = 50,nullable = false)
    private String displayName;

    @Column(name = "description", length = 100)
    private String description;





    @ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )

    private java.util.List<Permission> permissions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "normalized_name"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private java.util.List<User> users;

}
