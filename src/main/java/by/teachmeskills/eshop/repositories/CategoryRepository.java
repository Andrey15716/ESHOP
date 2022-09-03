package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getCategoryById(int id);
}