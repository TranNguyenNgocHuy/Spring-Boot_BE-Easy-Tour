package com.easy.tour.repository;

import com.easy.tour.Enum.RoleName;
import com.easy.tour.entity.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByRoleName(RoleName roleName);

    Role findByRoleName(RoleName roleName);
}
