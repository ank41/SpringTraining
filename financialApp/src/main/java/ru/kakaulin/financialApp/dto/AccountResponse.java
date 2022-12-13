package ru.kakaulin.financialApp.dto;

import java.util.List;

public class AccountResponse {

    private List<AccountDTO> accounts;

    public AccountResponse(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
