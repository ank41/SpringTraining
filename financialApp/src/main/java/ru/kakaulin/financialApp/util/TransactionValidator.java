package ru.kakaulin.financialApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kakaulin.financialApp.models.Transactions;
import ru.kakaulin.financialApp.services.OrderService;

@Component
public class TransactionValidator implements Validator {

    private final OrderService orderService;

    @Autowired
    public TransactionValidator(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Transactions.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
            Transactions transaction = (Transactions) o;
            if (transaction.getCashOrder()==null) {
                return;
            }

    }
}
