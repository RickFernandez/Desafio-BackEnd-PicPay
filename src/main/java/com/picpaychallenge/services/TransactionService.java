package com.picpaychallenge.services;

import com.picpaychallenge.dtos.requests.TransactionRequest;
import com.picpaychallenge.persistence.entities.Transaction;
import com.picpaychallenge.persistence.entities.User;
import com.picpaychallenge.persistence.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    UserService userService;

    @Autowired
    AuthorizationService authService;

    @Autowired
    TransactionRepository repository;

    @Autowired
    NotificationService notification;

    public Transaction createTransaction(TransactionRequest transactionRequest) throws Exception {
        User sender = userService.findUserById(transactionRequest.senderId());
        User receiver = userService.findUserById(transactionRequest.receiverId());

        userService.validateTransaction(sender, transactionRequest.value());

        boolean isAuthorized = authService.authorizeTransaction(sender, transactionRequest.value());
        if(!isAuthorized) {
            throw new Exception("Transação não autorizada.");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionRequest.value()));
        receiver.setBalance(receiver.getBalance().add(transactionRequest.value()));

        repository.save(transaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        notification.sendNotification(sender, "Transação realizada com sucesso!");
        notification.sendNotification(receiver, "Transação recebida com sucesso!");

        return transaction;
    }
}
