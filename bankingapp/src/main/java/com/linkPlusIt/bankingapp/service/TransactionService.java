package com.linkPlusIt.bankingapp.service;

import com.linkPlusIt.bankingapp.dto.DepositRequestDTO;
import com.linkPlusIt.bankingapp.dto.TransactionDTO;
import com.linkPlusIt.bankingapp.dto.TransferRequestDTO;
import com.linkPlusIt.bankingapp.dto.WithdrawalRequestDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO transferFunds(TransferRequestDTO request);
    TransactionDTO depositFunds(DepositRequestDTO depositRequestDTO);
    TransactionDTO withdrawFunds(WithdrawalRequestDTO withdrawalRequestDTO);
    List<TransactionDTO> getAccountTransactions(Long accountId);
}
