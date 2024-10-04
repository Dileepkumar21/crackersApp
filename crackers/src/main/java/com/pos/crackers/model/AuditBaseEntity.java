package com.pos.crackers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AuditBaseEntity {

    @JsonIgnore
    @Column(name = "create_ts", updatable = false)
    private LocalDateTime createTs;

    @JsonIgnore
    @Column(name = "update_ts")
    private LocalDateTime updateTs;

    @PrePersist // Automatically set the create timestamp when the entity is persisted
    protected void onCreate() {
        createTs = LocalDateTime.now();
        updateTs = LocalDateTime.now(); // Set both during creation
    }

    @PreUpdate // Automatically update the timestamp when the entity is updated
    protected void onUpdate() {
        updateTs = LocalDateTime.now();
    }

    // Getters and setters
    public LocalDateTime getCreateTs() {
        return createTs;
    }

    public void setCreateTs(LocalDateTime createTs) {
        this.createTs = createTs;
    }

    public LocalDateTime getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(LocalDateTime updateTs) {
        this.updateTs = updateTs;
    }
}
