package com.ugur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ugur.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     *
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);
}
