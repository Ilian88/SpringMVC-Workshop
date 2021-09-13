package com.example.judgev2_alone.repository;

import com.example.judgev2_alone.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsernameAndPassword(String username,String password);

    @Query("SELECT u.username FROM User u ORDER BY u.username")
    List<String> getAllNames();

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    @Query("SELECT u.username FROM User u ORDER BY u.homeworks.size DESC")
    Set<String> findTop3UsersByHomework();

    @Query("SELECT count(u.id) FROM User u")
    Integer getUserCount();
}
