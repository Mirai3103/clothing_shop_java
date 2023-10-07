package com.shop.clothing.common;

import com.shop.clothing.config.CurrentUserService;
import com.shop.clothing.config.ICurrentUserService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.aspectj.ConfigurableObject;

@Configurable
public class AuditingEntityListener implements ConfigurableObject {
    @Autowired
    private  ICurrentUserService currentUserService;

    @PrePersist
    public void touchForCreate(AuditableEntity target) {
        var currentUserId ="system";
        if (currentUserService != null) {
            currentUserId = currentUserService.getCurrentUserId().orElse("system");
        }
        target.setCreatedBy(currentUserId);
        target.setCreatedDate(java.time.LocalDateTime.now());
    }
    @PreUpdate
    public void touchForUpdate(AuditableEntity target) {
        var currentUserId ="system";
        if (currentUserService != null) {
            currentUserId = currentUserService.getCurrentUserId().orElse("system");
        }
        target.setLastModifiedBy(currentUserId);
        target.setLastModifiedDate(java.time.LocalDateTime.now());
    }
}
