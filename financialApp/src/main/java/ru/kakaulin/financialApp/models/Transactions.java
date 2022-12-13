package ru.kakaulin.financialApp.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "result")
    private String result;

    @Column(name = "type_transaction")
    private String type;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @Nullable
    private Order order;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "account_to_id", referencedColumnName = "account_id")
    private Account accountTo;

    public Transactions() {
    }

    public Transactions(BigDecimal sum, String result, String type, Account account) {
        this.sum = sum;
        this.result = result;
        this.type = type;
        this.account = account;
    }

    public Transactions(BigDecimal sum, String result, String type, Account account, Order order) {
        this.sum = sum;
        this.result = result;
        this.type = type;
        this.account = account;
        this.order = order;
    }

    public Transactions(BigDecimal sum, String result, String type, Account account, Account accountTo) {
        this.sum = sum;
        this.result = result;
        this.type = type;
        this.account = account;
        this.accountTo = accountTo;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public Order getCashOrder() {
        return order;
    }

    public void setCashOrder(Order order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
