package ru.kakaulin.financialApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kakaulin.financialApp.models.Account;
import ru.kakaulin.financialApp.models.Transactions;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
    List<Transactions> findAllTransactionsByAccount(Account account);

    List<Transactions> findAll();
}
