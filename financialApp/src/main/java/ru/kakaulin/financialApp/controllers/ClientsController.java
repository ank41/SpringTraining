package ru.kakaulin.financialApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kakaulin.financialApp.dto.ClientDTO;
import ru.kakaulin.financialApp.dto.ClientResponse;
import ru.kakaulin.financialApp.models.Client;
import ru.kakaulin.financialApp.services.AccountService;
import ru.kakaulin.financialApp.services.ClientService;
import ru.kakaulin.financialApp.util.accountUtils.AccountErrorResponse;
import ru.kakaulin.financialApp.util.accountUtils.AccountException;
import ru.kakaulin.financialApp.util.clientUtils.ClientValidator;
import ru.kakaulin.financialApp.util.accountUtils.ErrorsUtil;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientService clientService;
    private final ModelMapper modelMapper;
    private final ClientValidator clientValidator;
    private final AccountService accountService;

    @Autowired
    public ClientsController(ClientService clientService, ModelMapper modelMapper, ClientValidator clientValidator, AccountService accountService) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
        this.clientValidator = clientValidator;
        this.accountService = accountService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid  ClientDTO clientDTO,
                                                   BindingResult bindingResult) {
        Client clientToAdd = convertToClient(clientDTO);
        clientValidator.validate(clientToAdd, bindingResult);
        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);
        clientService.register(clientToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public ClientResponse getAllClients(){

        return new ClientResponse(clientService.findAll()
                .stream()
                .map(this::convertToClientDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ClientResponse getClientById( @PathVariable(name = "id") int id){
        return new ClientResponse(Arrays.asList(convertToClientDTO(clientService.findOne(id))));
    }



    @ExceptionHandler
    private ResponseEntity<AccountErrorResponse> handleException(AccountException e) {
        AccountErrorResponse response = new AccountErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private ClientDTO convertToClientDTO(Client client){
        return modelMapper.map(client, ClientDTO.class);
    }

    private Client convertToClient(ClientDTO clientDTO){
        return modelMapper.map(clientDTO, Client.class);
    }
}
