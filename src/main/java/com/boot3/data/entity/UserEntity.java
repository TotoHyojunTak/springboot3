package com.boot3.data.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@ToString
@Entity
@Table(name = "TB_USERS")
public class UserEntity {
    @Id
    @Column(nullable = false, unique = true)
    private String userId;

    private String email;

    private String name;

    private String encryptedPwd;

    @CreatedDate
    private LocalDateTime createdDate;

}