package com.easy.tour.repository;

import com.easy.tour.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
