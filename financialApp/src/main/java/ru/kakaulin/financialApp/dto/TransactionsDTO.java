package ru.kakaulin.financialApp.dto;

import jakarta.annotation.Nullable;
import ru.kakaulin.financialApp.models.Account;
import ru.kakaulin.financialApp.models.Order;

import java.math.BigDecimal;

public class TransactionsDTO {

    private BigDecimal sum;

    private String result;

    private String type;

    private Account account;

    @Nullable
    private Order order;

    private Account accountTo;


    private String codeword;

    private String accountNumber;

    @Nullable
    private String accountNumberTo;

    public TransactionsDTO(BigDecimal sum, String result, String type) {
        this.sum = sum;
        this.result = result;
        this.type = type;
    }

    public TransactionsDTO(BigDecimal sum, String result, String type, Account account) {
        this.sum = sum;
        this.result = result;
        this.type = type;
        this.account = account;
    }

    public TransactionsDTO() {
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getCodeword() {
        return codeword;
    }

    public void setCodeword(String codeword) {
        this.codeword = codeword;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumberTo() {
        return accountNumberTo;
    }

    public void setAccountNumberTo(String accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
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
