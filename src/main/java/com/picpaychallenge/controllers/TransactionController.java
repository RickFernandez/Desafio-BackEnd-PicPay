package com.picpaychallenge.controllers;

import com.picpaychallenge.dtos.requests.TransactionRequest;
import com.picpaychallenge.persistence.entities.Transaction;
import com.picpaychallenge.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest transactionRequest) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(transactionRequest);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
