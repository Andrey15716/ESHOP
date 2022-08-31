package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    Product getProductById(int id) throws RepositoryExceptions;

    Page<Product> findAllByCategoryId(int categoryId, Pageable pageable);

    void deleteProductById(int id);
}