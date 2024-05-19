package com.linkPlusIt.bankingapp.controller;

import com.linkPlusIt.bankingapp.dto.DepositRequestDTO;
import com.linkPlusIt.bankingapp.dto.TransactionDTO;
import com.linkPlusIt.bankingapp.dto.TransferRequestDTO;
import com.linkPlusIt.bankingapp.dto.WithdrawalRequestDTO;
import com.linkPlusIt.bankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDTO> transferFunds(@RequestBody TransferRequestDTO request) {
        TransactionDTO transactionDTO = transactionService.transferFunds(request);
        return ResponseEntity.ok(transactionDTO);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getAccountTransactions(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getAccountTransactions(accountId));
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> depositFunds(@RequestBody DepositRequestDTO requestDTO) {
        TransactionDTO transaction = transactionService.depositFunds(requestDTO);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdrawFunds(@RequestBody WithdrawalRequestDTO requestDTO) {
        TransactionDTO transaction = transactionService.withdrawFunds(requestDTO);
        return ResponseEntity.ok(transaction);
    }
}
