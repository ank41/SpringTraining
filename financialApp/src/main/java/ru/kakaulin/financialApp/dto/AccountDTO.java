package ru.kakaulin.financialApp.dto;

import org.hibernate.validator.constraints.UniqueElements;
import ru.kakaulin.financialApp.models.Order;
import ru.kakaulin.financialApp.models.Client;
import ru.kakaulin.financialApp.models.Transactions;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AccountDTO {

    @NotNull
    private Client client;

    @NotNull
    @UniqueElements
    private String accountNumber;

    private BigDecimal accountBalance;

    private String type;

    @NotNull
    private LocalDateTime creationTime;

    @NotNull
    private LocalDateTime validity;


    private List<Order> orders;


    private List<Transactions> transactions;


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getValidity() {
        return validity;
    }

    public void setValidity(LocalDateTime validity) {
        this.validity = validity;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "client=" + client +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountBalance=" + accountBalance +
                ", type='" + type + '\'' +
                ", creationTime=" + creationTime +
                ", validity=" + validity +
                ", orders=" + orders +
                ", transactions=" + transactions +
                '}';
    }
}
