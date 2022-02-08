package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Rollback(false)
public class RoleRepositoryTest {

    @Autowired RoleRepository roleRepository;

    @Test
    void createFirstRoleTest() {
        Role role = new Role("Admin", "manage everything");
        Role savedRole = roleRepository.save(role);

        Assertions.assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    void createRolesTest() {
        Role salesPerson = new Role("SalesPerson", "manage product price, customers, shipping, orders and sales report");
        Role salesEditor = new Role("Editor", "manage categories, brands, products, articles and menus");
        Role shipper = new Role("Shipper", "view products, view orders and update order status");
        Role assistant = new Role("Assistant", "manage questions and reviews");

        roleRepository.saveAll(List.of(salesPerson, salesEditor, shipper, assistant));
    }
}
