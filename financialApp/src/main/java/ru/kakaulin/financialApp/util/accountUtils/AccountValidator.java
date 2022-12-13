package ru.kakaulin.financialApp.util.accountUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kakaulin.financialApp.models.Account;
import ru.kakaulin.financialApp.services.ClientService;

@Component
public class AccountValidator implements Validator {

    private final ClientService clientService;

    @Autowired
    public AccountValidator(ClientService sensorService) {
        this.clientService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Account account = (Account) o;

        if (account.getClient() == null) {
            return;
        }

        if (clientService.findOne(account.getClient().getId())==null)
            errors.rejectValue("client", "Нет клиента с таким идентификатором!");
    }
}
