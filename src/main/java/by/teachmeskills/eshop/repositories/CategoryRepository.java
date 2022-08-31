package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getCategoryById(int id) throws RepositoryExceptions;
}