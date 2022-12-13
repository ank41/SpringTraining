package ru.kakaulin.financialApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kakaulin.financialApp.models.Account;
import ru.kakaulin.financialApp.models.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findById(int id);

    List<Order> findAllOrdersByAccount(Account account);
}
