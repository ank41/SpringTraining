package ru.kakaulin.financialApp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kakaulin.financialApp.models.Account;
import ru.kakaulin.financialApp.models.Order;
import ru.kakaulin.financialApp.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;



    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void addOrder(Order order) {

        orderRepository.save(order);

    }
       public List<Order> findAllOrdersByAccount(Account account){
           return orderRepository.findAllOrdersByAccount(account);
       }

    public Order fillOrder(Order order, String result, Account account){
        order.setResult(result);
        order.setAccount(account);
        order.setCreationTime(LocalDateTime.now());
        return order;
    }






}
