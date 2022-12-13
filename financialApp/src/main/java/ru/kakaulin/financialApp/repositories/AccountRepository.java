package ru.kakaulin.financialApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kakaulin.financialApp.models.Account;
import ru.kakaulin.financialApp.models.Client;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findById(Integer id);

    List<Account> findAllAccountByClientId(Client client);
    Optional<Account> findByNumber(String number);


}
