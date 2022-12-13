package ru.kakaulin.financialApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kakaulin.financialApp.models.Order;
import ru.kakaulin.financialApp.services.AccountService;
import ru.kakaulin.financialApp.services.OrderService;

@Component
public class OrderValidator implements Validator {

    private final OrderService orderService;
    private final AccountService accountService;

    @Autowired
    public OrderValidator(OrderService orderService, AccountService accountService) {
        this.orderService = orderService;
        this.accountService = accountService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Order.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Order order = (Order) o;
       // if(accountService.findById(order.getOrder().getId())==null)
         //   errors.rejectValue("id", "Нет счета с таким идентификатором");
    }

}
