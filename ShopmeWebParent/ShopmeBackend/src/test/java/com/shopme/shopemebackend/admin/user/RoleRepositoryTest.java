package com.shopme.shopemebackend.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.shopmebackend.admin.user.RoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired RoleRepository roleRepository;

    @Test
    void createFirstRoleTest() {
        Role role = new Role("Admin", "manage everything");
        Role savedRole = roleRepository.save(role);

        Assertions.assertThat(savedRole.getId()).isGreaterThan(0);
    }


}
