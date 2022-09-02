package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Page<Order> getOrdersByUserId(int userId, Pageable pageable) throws RepositoryExceptions;

    List<Order> getOrdersByUserId(int userId) throws RepositoryExceptions;
}