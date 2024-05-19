package com.linkPlusIt.bankingapp.controller;

import com.linkPlusIt.bankingapp.dto.AccountDTO;
import com.linkPlusIt.bankingapp.dto.BankDTO;
import com.linkPlusIt.bankingapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping
    public ResponseEntity<BankDTO> createBank(@RequestBody BankDTO bankDTO) {
        return ResponseEntity.ok(bankService.createBank(bankDTO));
    }

    @GetMapping
    public ResponseEntity<List<BankDTO>> getAllBanks() {
        return ResponseEntity.ok(bankService.getAllBanks());
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<AccountDTO>> getAllBanksWithAccounts(@PathVariable Long id) {
        List<AccountDTO> banks = bankService.findAccountsByBankId(id);
        return ResponseEntity.ok(banks);
    }

    @GetMapping("/{id}/total-fees")
    public ResponseEntity<BigDecimal> getTotalTransactionFees(@PathVariable Long id) {
        BigDecimal totalFees = bankService.getTotalTransactionFeeAmount(id);
        return ResponseEntity.ok(totalFees);
    }

    @GetMapping("/{id}/total-transfers")
    public ResponseEntity<BigDecimal> getTotalTransferAmount(@PathVariable Long id) {
        BigDecimal totalTransfers = bankService.getTotalTransferAmount(id);
        return ResponseEntity.ok(totalTransfers);
    }

}
