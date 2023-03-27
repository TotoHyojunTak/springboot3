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
@ToString
@Getter
@Entity
@Table(name = "TB_FRUIT")
public class FruitEntity {
    @Id
    @Column(nullable = false, unique = true)
    private Integer seq;

    private String name;

    private String season;

    private Integer price;

    private String region;

    @CreatedDate
    private LocalDateTime createdDate;

}