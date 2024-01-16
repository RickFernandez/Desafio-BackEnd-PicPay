package com.picpaychallenge.services;

import com.picpaychallenge.dtos.requests.UserRequest;
import com.picpaychallenge.enums.UserType;
import com.picpaychallenge.persistence.entities.User;
import com.picpaychallenge.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() == UserType.MERCHANT) {
          throw new Exception("Usuário do tipo 'Logista' não está autorizado a realizar transações.");
        }
        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return (User) repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    public User createUser(UserRequest userRequest) {
        User newUser = new User(userRequest);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return  repository.findAll();
    }

    public void saveUser(User user) {
        repository.save(user);
    }

}
