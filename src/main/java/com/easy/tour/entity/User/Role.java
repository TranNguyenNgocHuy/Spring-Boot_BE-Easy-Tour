package com.easy.tour.entity.User;

import com.easy.tour.Enum.RoleName;
import com.easy.tour.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "Role_Name")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;


    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

   public String getRoleName() {
       return roleName.toString();
   }
}
