package ru.kakaulin.financialApp.util.clientUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kakaulin.financialApp.models.Client;
import ru.kakaulin.financialApp.services.ClientService;

@Component
public class ClientValidator implements Validator {

    private final ClientService clientService;

    @Autowired
    public ClientValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
       // Client client = (Client) o;
        // if(clientService.findOne(client.getId())==null)
        //    errors.rejectValue("id", "Нет клиента с таким идентификатором");
    }
}
