package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public List<Role> findRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        encodePassword(user);
        userRepository.save(user);
    }

    public boolean isUniqueEmail(Long userId, String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) return true;
        if (user.get().getId().equals(userId)) return true; // edit

        return false;
    }

    public User findUser(Long userId) {
        try {
            return userRepository.findById(userId).get();
        } catch (Exception e) {
            throw new UserNotFoundException(UserNotFoundException.NOT_FOUND_MESSAGE);
        }
    }

    @Transactional
    public void updateUser(User user) {
        if (StringUtils.hasText(user.getPassword())) encodePassword(user);

        User findUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new UserNotFoundException(UserNotFoundException.NOT_FOUND_MESSAGE)
        );

        findUser.update(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        Long exist = userRepository.countById(id);
        if (Objects.isNull(exist) || exist <= 0) {
            throw new UserNotFoundException(UserNotFoundException.NOT_FOUND_MESSAGE);
        }

        userRepository.deleteById(id);
    }

    @Transactional
    public void editEnabledStatus(Long userId, boolean enabled) {
        userRepository.updateEnabledStatus(userId, enabled);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }


}
