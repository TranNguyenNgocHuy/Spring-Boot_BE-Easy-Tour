package com.easy.tour.repository;

import com.easy.tour.Enum.RoleName;
import com.easy.tour.entity.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleName roleName);
}
