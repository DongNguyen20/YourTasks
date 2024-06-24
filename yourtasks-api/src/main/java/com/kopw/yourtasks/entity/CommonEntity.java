package com.kopw.yourtasks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
public class CommonEntity implements Serializable {
    @CreatedBy
    @Column(name = "created_by")
    @Builder.Default
    private String createdBy = "admin";

    @CreatedDate
    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedBy
    @Column(name = "updated_by")
    @Builder.Default
    private String updatedBy = "admin";

    @LastModifiedDate
    @Column(name = "updated_at")
    @Builder.Default
    private LocalDateTime updatedDate = LocalDateTime.now();
}
