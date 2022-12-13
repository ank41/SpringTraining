package ru.kakaulin.financialApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kakaulin.financialApp.models.Account;
import ru.kakaulin.financialApp.models.Transactions;
import ru.kakaulin.financialApp.repositories.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transactions> findAllTransactionsByAccount(Account account){
        return transactionRepository.findAllTransactionsByAccount(account);
    }

    public List<Transactions> findAll(){return transactionRepository.findAll();}

    @Transactional
    public void add(Transactions transactions) {
        transactions.setCreationTime(LocalDateTime.now());
        transactionRepository.save(transactions);
    }
}
