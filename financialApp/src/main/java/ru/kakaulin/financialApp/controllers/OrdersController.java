package ru.kakaulin.financialApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kakaulin.financialApp.dto.OrderDTO;
import ru.kakaulin.financialApp.dto.OrderResponse;
import ru.kakaulin.financialApp.models.Order;
import ru.kakaulin.financialApp.services.AccountService;
import ru.kakaulin.financialApp.services.OrderService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrdersController {


    private final ModelMapper modelMapper;
    private final OrderService orderService;
    private final AccountService accountService;


    public OrdersController(ModelMapper modelMapper, OrderService orderService, AccountService accountService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
        this.accountService = accountService;
    }

    @GetMapping(value = "/account/{id}")
    public OrderResponse getAllTransactions(@PathVariable(name = "id") String accountNumber){

        return new OrderResponse(orderService.findAllOrdersByAccount(accountService.findByNumber(accountNumber))
                .stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList()));

    }

    private OrderDTO convertToOrderDTO(Order order){
        return modelMapper.map(order, OrderDTO.class);
    }
}
