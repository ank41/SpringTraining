package ru.kakaulin.financialApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kakaulin.financialApp.dto.AccountDTO;
import ru.kakaulin.financialApp.dto.AccountResponse;
import ru.kakaulin.financialApp.models.Account;
import ru.kakaulin.financialApp.services.AccountService;
import ru.kakaulin.financialApp.services.ClientService;
import ru.kakaulin.financialApp.util.accountUtils.AccountValidator;
import ru.kakaulin.financialApp.util.accountUtils.ErrorsUtil;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private final ModelMapper modelMapper;
    private final AccountService accountService;
    private final ClientService clientService;
    private final AccountValidator accountValidator;
    @Autowired
   public AccountsController(ModelMapper modelMapper, AccountService accountService, ClientService clientService, AccountValidator accountValidator) {
       this.modelMapper = modelMapper;
        this.accountService = accountService;
        this.clientService = clientService;
        this.accountValidator = accountValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid AccountDTO accountDTO,
                                                   BindingResult bindingResult) {
        Account accountToAdd = convertToAccount(accountDTO);
        accountValidator.validate(accountToAdd, bindingResult);
        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);
        accountService.register(accountToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public AccountResponse getAllAccounts(){
        return new AccountResponse(accountService.findAll()
                .stream()
                .map(this::convertToAccountDTO)
                .collect(Collectors.toList()));
    }
    @GetMapping(value = "client/{id}")
   public AccountResponse getAccountsClient(@PathVariable(name = "id") int id){

        return new AccountResponse(accountService.findAllAccountsClient(clientService.findOne(id))
                .stream()
                .map(this::convertToAccountDTO)
                .collect(Collectors.toList() ));
  }
    @GetMapping(value = "/{id}")
    public AccountResponse getAccountById( @PathVariable(name = "id") int id){
        return new AccountResponse(Arrays.asList(convertToAccountDTO(accountService.findById(id))));
    }

    public ResponseEntity<HttpStatus> update(AccountDTO accountDTO) {
        Account account = convertToAccount(accountDTO);
        accountService.register(account);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> update(Account account) {
        accountService.register(account);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public AccountDTO convertToAccountDTO(Account account){
        return modelMapper.map(account, AccountDTO.class);
    }
    public Account convertToAccount(AccountDTO accountDTO){
        return modelMapper.map(accountDTO, Account.class);
    }



}
