package com.example.projectDemo.Repositories;

import com.example.projectDemo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE %?1%"
            + " OR LOWER(u.phone) LIKE %?1%"
            + " OR LOWER(u.address) LIKE %?1%"
            + " OR LOWER(u.email) LIKE %?1%")
//    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', ?1, '%'))"
//            + " OR LOWER(u.phone) LIKE LOWER(CONCAT('%', ?1, '%'))"
//            + " OR LOWER(u.address) LIKE LOWER(CONCAT('%', ?1, '%'))"
//            + " OR LOWER(u.email) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<User> search(String keyword);

    User findByEmail(String email);

    User findById(long id);

    User save(User person);

    User deleteById(long id);




}
