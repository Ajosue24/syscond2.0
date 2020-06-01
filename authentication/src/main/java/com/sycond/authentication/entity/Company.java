package com.sycond.authentication.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="companies")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "address",columnDefinition = "text")
    @Getter
    @Setter
    private String address;

    @Column(name="email")
    @Email
    @NotNull
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Getter
    @Setter
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    @Getter
    @Setter
    private LocalDateTime deletedAt;

    @Column(name= "trade_name")
    @NotNull
    @Getter
    @Setter
    private String tradeName;
    @Column(name = "business_name")
    @NotNull
    @Getter
    @Setter
    private String businessName;

    @Column(name = "phone_number")
    @Getter
    @Setter
    private String phoneNumber;

    @Column(name = "enable")
    @Getter
    @Setter
    private Boolean enable = true;

}
