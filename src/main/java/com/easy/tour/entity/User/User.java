package com.easy.tour.entity.User;

import com.easy.tour.entity.BaseEntity;
import lombok.*;

import jakarta.persistence.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private Long userId;

    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Gender")
    private Boolean gender;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Avatar_Img")
    private String avatarImg;

    @Column(name = "Role_Name", nullable = false, columnDefinition = "TINYINT")
    private byte userRole;
}
