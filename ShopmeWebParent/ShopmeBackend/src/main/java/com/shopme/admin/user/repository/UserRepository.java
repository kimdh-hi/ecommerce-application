package com.shopme.admin.user.repository;

import com.shopme.common.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    Long countById(Long id);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.enabled = :enabled where u.id = :id")
    void updateEnabledStatus(Long id, boolean enabled);
}
