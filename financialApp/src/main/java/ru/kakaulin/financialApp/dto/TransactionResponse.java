package ru.kakaulin.financialApp.dto;

import java.util.List;

public class TransactionResponse {
    private List<TransactionsDTO> transactions;

    public TransactionResponse(List<TransactionsDTO> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionsDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionsDTO> transactions) {
        this.transactions = transactions;
    }
}
