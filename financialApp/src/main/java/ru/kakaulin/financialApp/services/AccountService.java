package ru.kakaulin.financialApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kakaulin.financialApp.models.Account;
import ru.kakaulin.financialApp.models.Client;
import ru.kakaulin.financialApp.repositories.AccountRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final  AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

   public Account findById(Integer id){
     return accountRepository.findById(id).get();
 }

    public List<Account> findAllAccountsClient(Client client){
        return accountRepository.findAllAccountByClientId(client);
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }


    public Account findByNumber(String accountNumber){
        return  accountRepository.findByNumber(accountNumber).get();
    }

    public Account AccountWithdrawalBalance(BigDecimal amount, Account account){
                account.setAccountBalance(account.getAccountBalance().subtract(amount));
                return account;
    }

    public String checkEnoughBalance(BigDecimal amount, BigDecimal balance, String result) {
        if (amount.compareTo(balance) != 1) {
            return result;
        }
        return "Not enough money in the account";
    }

    @Transactional
    public void register(Account account) {
       accountRepository.save(account);
    }

}

