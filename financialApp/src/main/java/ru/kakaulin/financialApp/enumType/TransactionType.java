package ru.kakaulin.financialApp.enumType;

public enum TransactionType {

    DEPOSIT(1), WITHDRAWAL(2), TRANSFER(3);
    int id;

    private TransactionType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
