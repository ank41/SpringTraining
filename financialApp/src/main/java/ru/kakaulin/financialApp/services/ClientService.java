package ru.kakaulin.financialApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kakaulin.financialApp.models.Client;
import ru.kakaulin.financialApp.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;
    PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();



    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findOne(Integer id){
        Optional<Client> foundClient = clientRepository.findById(id);
        return foundClient.orElse(null);
    }



    public String checkCodeword(String codeword, String clientCodeWord)
    {

        if(passwordEncoder.matches(codeword, clientCodeWord)){
            return "successfully";
        }
        else
            return "invalid code word";
    }
    @Transactional
    public void register(Client client) {
        client.setCodeword(passwordEncoder.encode(client.getCodeword()));

        clientRepository.save(client);
    }



}
