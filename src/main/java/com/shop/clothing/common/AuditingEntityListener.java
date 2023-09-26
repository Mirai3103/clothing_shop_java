package com.shop.clothing.common;

import com.shop.clothing.config.CurrentUserService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.aspectj.ConfigurableObject;

@Configurable
public class AuditingEntityListener implements ConfigurableObject {
    private CurrentUserService currentUserService;
    @Autowired
    public void setCurrentUserService(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }
    @PrePersist
    public void touchForCreate(AuditableEntity target) {
        target.setCreatedBy(currentUserService.getCurrentUser().orElse("system"));
        target.setCreatedDate(java.time.LocalDateTime.now());
    }
    @PreUpdate
    public void touchForUpdate(AuditableEntity target) {
        target.setLastModifiedBy(currentUserService.getCurrentUser().orElse("system"));
        target.setLastModifiedDate(java.time.LocalDateTime.now());
    }
}
