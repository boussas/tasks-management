
package com.boussas.tasks.service;
import com.boussas.tasks.model.User;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);

    User updateUserInfo(Long id, String firstName, String lastName, String email);

    void deleteUserById(Long id);

    User updatePassword(Long id, String newPassword);
}