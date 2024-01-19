package com.picpaychallenge.services;

import com.picpaychallenge.dtos.requests.TransactionRequest;
import com.picpaychallenge.enums.UserTypeEnum;
import com.picpaychallenge.persistence.entities.Transaction;
import com.picpaychallenge.persistence.entities.User;
import com.picpaychallenge.persistence.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    UserService userService;

    @Mock
    AuthorizationService authService;

    @Mock
    TransactionRepository repository;

    @Mock
    NotificationService notification;

    @Autowired
    @InjectMocks
    TransactionService transactionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create Transaction successfully when everything is OK")
    void createTransactionCase1() throws Exception {
        User sender = new User(1L, "Sender", "Test", "123.456.789-10", "sender@test.com", "Senha123", new BigDecimal(10), UserTypeEnum.COMMON);
        User receiver = new User(2L, "Receiver", "Test", "123.456.789-11", "receiver@test.com", "Senha123", new BigDecimal(10), UserTypeEnum.MERCHANT);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authService.authorizeTransaction(any(), any())).thenReturn(true);

        TransactionRequest request = new TransactionRequest(new BigDecimal(10), 1L, 2L);
        transactionService.createTransaction(request);

        verify(repository, times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        verify(userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal(20));
        verify(userService, times(1)).saveUser(receiver);

        verify(notification, times(1)).sendNotification(sender, "Transação realizada com sucesso!");
        verify(notification, times(1)).sendNotification(receiver, "Transação recebida com sucesso!");
    }

    @Test
    @DisplayName("Should thow Exception when Transaction is not allowed")
    void createTransactionCase2() throws Exception {
        User sender = new User(1L, "Sender", "Test", "123.456.789-10", "sender@test.com", "Senha123", new BigDecimal(10), UserTypeEnum.COMMON);
        User receiver = new User(2L, "Receiver", "Test", "123.456.789-11", "receiver@test.com", "Senha123", new BigDecimal(10), UserTypeEnum.MERCHANT);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authService.authorizeTransaction(any(), any())).thenReturn(false);

        Exception thrown = assertThrows(Exception.class, () -> {
            TransactionRequest request = new TransactionRequest(new BigDecimal(10), 1L, 2L);
            transactionService.createTransaction(request);
        });

        assertEquals("Transação não autorizada.", thrown.getMessage());
    }
}