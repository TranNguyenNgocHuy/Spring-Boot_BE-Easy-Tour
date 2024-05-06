package com.easy.tour.entity.User;

import com.easy.tour.Enum.RoleName;
import com.easy.tour.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_Id")
    private Long roleId;

    @Column(name = "Role_Name")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private Set<User> users = new HashSet<>();

    public String getRoleName() {
       return roleName.toString();
   }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }
}
