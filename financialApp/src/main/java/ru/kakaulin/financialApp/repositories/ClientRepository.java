package ru.kakaulin.financialApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kakaulin.financialApp.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
