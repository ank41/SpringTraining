package ru.kakaulin.financialApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kakaulin.financialApp.dto.OrderDTO;
import ru.kakaulin.financialApp.dto.TransactionsDTO;
import ru.kakaulin.financialApp.models.Account;
import ru.kakaulin.financialApp.models.Order;
import ru.kakaulin.financialApp.models.Transactions;
import ru.kakaulin.financialApp.services.AccountService;
import ru.kakaulin.financialApp.services.ClientService;
import ru.kakaulin.financialApp.services.OrderService;
import ru.kakaulin.financialApp.services.TransactionService;
import ru.kakaulin.financialApp.util.accountUtils.ErrorsUtil;
import ru.kakaulin.financialApp.util.OrderValidator;
import ru.kakaulin.financialApp.util.TransactionValidator;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/operations")
public class OperationsController {

 private final  OrderService orderService;
 private final OrderValidator orderValidator;
 private final AccountService accountService;
 private final AccountsController accountsController;
 private final ModelMapper modelMapper;
 private final TransactionValidator transactionValidator;
 private final ClientService clientService;
 private final TransactionService transactionService;

     @Autowired
    public OperationsController(OrderService orderService, OrderValidator orderValidator, AccountService accountService, AccountsController accountsController, ModelMapper modelMapper, TransactionValidator transactionValidator, ClientService clientService, TransactionService transactionService) {
        this.orderService = orderService;
        this.orderValidator = orderValidator;
         this.accountService = accountService;
         this.accountsController = accountsController;
         this.modelMapper = modelMapper;
         this.transactionValidator = transactionValidator;
         this.clientService = clientService;
         this.transactionService = transactionService;
     }


    @PostMapping("/operationWithAccount")
    public ResponseEntity<HttpStatus> createWithdrawDeposit(@RequestBody @Valid OrderDTO orderDTO,
                                          BindingResult bindingResult) {
         String result;
         Account account = accountService.findByNumber(orderDTO.getAccountNumber());

         if(orderDTO.getType().toUpperCase(Locale.ROOT).equals("WITHDRAWAL")) {

              result = clientService.checkCodeword(orderDTO.getCodeword(), account.getClient().getCodeword());


            if (result.equals("successfully")) {

                result = accountService.checkEnoughBalance(orderDTO.getSum(), account.getAccountBalance(), result);

                if (result.equals("successfully")) {

                    account = accountService.AccountWithdrawalBalance(orderDTO.getSum(),  account);


                }
            }
        }

        else if(orderDTO.getType().toUpperCase(Locale.ROOT).equals("DEPOSIT"))
        {
            account = accountService.AccountWithdrawalBalance(orderDTO.getSum().negate(),  account);
            accountsController.update(account);

            result = "successfully";
        }
        else { result= "Invalid type operation";}

        addTransactionForOrder(orderService.fillOrder(convertToOrder(orderDTO), result, accountService.findByNumber(orderDTO.getAccountNumber())));

        //orderValidator.validate(orderDTO, bindingResult);
        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<HttpStatus> createTransfer(@RequestBody @Valid TransactionsDTO transactionsDTO,
                                          BindingResult bindingResult) {
        String result;

        Account account= accountService.findByNumber(transactionsDTO.getAccountNumber());
        Account accountTo = accountService.findByNumber(transactionsDTO.getAccountNumberTo());

        if(transactionsDTO.getType().toUpperCase(Locale.ROOT).equals("TRANSFER")) {

            result = clientService.checkCodeword(transactionsDTO.getCodeword(), account.getClient().getCodeword());

            if (result.equals("successfully")) {

                result = accountService.checkEnoughBalance(transactionsDTO.getSum(), account.getAccountBalance(), result);

                if (result.equals("successfully")) {

                    account = accountService.AccountWithdrawalBalance(transactionsDTO.getSum(), account);
                    accountTo = accountService.AccountWithdrawalBalance(transactionsDTO.getSum().negate(), accountTo);

                    accountsController.update(account);
                    accountsController.update(accountTo);

                }
            }
        }
        else { result= "Invalid type operation";}

        addTransaction(transactionsDTO, account, accountTo, result);

        //orderValidator.validate(order, bindingResult);
        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private ResponseEntity<HttpStatus> addTransactionForOrder(Order order) {

         Transactions transaction = new Transactions(order.getSum(), order.getResult(),
                order.getType(),order.getAccount(), order);

        transactionService.add(transaction);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private ResponseEntity<HttpStatus> addTransaction(TransactionsDTO transactionsDTO, Account account, Account accountTo, String result) {

        Transactions transaction = new Transactions(transactionsDTO.getSum(), result,
                transactionsDTO.getType(), account);

        transactionService.add(transaction);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private ResponseEntity<HttpStatus> addOrder(Order order) {

        orderService.addOrder(order);
        return ResponseEntity.ok(HttpStatus.OK);
    }




    private Order convertToOrder(OrderDTO orderDTO){
        return modelMapper.map(orderDTO, Order.class);
    }

}
