package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserByName(String name);

    void deleteUserById(int id);
}