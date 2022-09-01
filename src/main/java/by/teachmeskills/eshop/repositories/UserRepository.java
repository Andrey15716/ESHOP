package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByNameAndPassword(String name, String password) throws RepositoryExceptions;

    Optional<User> getUserByName(String name);

    void deleteUserById(int id);
}