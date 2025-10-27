package com.sil.digitalbankingbackend.controllers;

import com.sil.digitalbankingbackend.entities.BankTransaction;
import com.sil.digitalbankingbackend.services.BankTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class  BankTransactionController {
    long start = System.currentTimeMillis();
    private  final  BankTransactionService service;



    @PostMapping("/excle/savebanktansaction")
    public ResponseEntity<String> saveTransactions(@RequestBody List<BankTransaction> transactions) {
        int chunkSize = 1000;
        service.saveAllMultiThreaded(transactions, chunkSize);
        long end = System.currentTimeMillis();
        long elapsed = end - start; // time in milliseconds

        System.out.println("Total insertion time: 123333333333333333333333333   is : " + elapsed + " ms");
        return ResponseEntity.ok("Transactions saved successfully using multi-threading!");
    }
}

