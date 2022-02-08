package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired UserRepository userRepository;
    @Autowired TestEntityManager tem;

    @Test
    void createUserWithOneRole() {
        Role roleAdmin = tem.find(Role.class, 1L);
        User user = new User("admin@shop.me.com", "1234", "daehuyn", "kim");
        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void createUserWithRoles() {
        User user = new User("kim@gmail.com", "kim123", "dh", "kim");
        Role roleEditor = new Role(3L);
        Role roleAssistant = new Role(5L);
        user.addRole(roleEditor, roleAssistant);

        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void allUserListTest() {
        Iterable<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }

    @Test
    void testGetUserById() {
        User user = userRepository.findById(1L).get();
        System.out.println(user);
        assertThat(user).isNotNull();
    }

    @Test
    void testDeleteById() {
        Long deleteId = 2L;
        userRepository.deleteById(deleteId);
    }

    @Test
    void findUserByEmailDuplicated() {
        String email = "admin@shop.me.com";
        User user = userRepository.findUserByEmail(email).get();

        assertThat(user).isNotNull();
    }

    @Test
    void findUserByEmailUnique() {
        String email = "no-exist-email@nono.nono";
        boolean empty = userRepository.findUserByEmail(email).isEmpty();

        assertThat(empty).isEqualTo(true);
    }
}
