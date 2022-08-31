package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByNameAndPassword(String name, String password) throws RepositoryExceptions;

    void deleteUserById(int id);
}