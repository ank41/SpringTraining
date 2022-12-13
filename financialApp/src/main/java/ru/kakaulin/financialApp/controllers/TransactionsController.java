package ru.kakaulin.financialApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kakaulin.financialApp.dto.TransactionResponse;
import ru.kakaulin.financialApp.dto.TransactionsDTO;
import ru.kakaulin.financialApp.models.Transactions;
import ru.kakaulin.financialApp.services.AccountService;
import ru.kakaulin.financialApp.services.TransactionService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private final ModelMapper modelMapper;
    private final TransactionService transactionService;
    private final AccountService accountService;

    public TransactionsController(ModelMapper modelMapper, TransactionService transactionService, AccountService accountService) {
        this.modelMapper = modelMapper;
        this.transactionService = transactionService;
        this.accountService = accountService;
    }


    @GetMapping()
    public TransactionResponse getAllAccounts(){
        return new TransactionResponse(transactionService.findAll()
                .stream()
                .map(this::convertToTransactionDTO)
                .collect(Collectors.toList()));
    }
    @GetMapping(value = "/{id}")
    public TransactionResponse getAllTransactions(@PathVariable(name = "id") String accountNumber){

        return new TransactionResponse(transactionService.findAllTransactionsByAccount(accountService.findByNumber(accountNumber))
                .stream()
                .map(this::convertToTransactionDTO)
                .collect(Collectors.toList()));

    }

    private TransactionsDTO convertToTransactionDTO(Transactions transactions){
        return modelMapper.map(transactions, TransactionsDTO.class);
    }

    private Transactions convertToTransaction(TransactionsDTO transactionsDTO){
        return modelMapper.map(transactionsDTO, Transactions.class);
    }
}
