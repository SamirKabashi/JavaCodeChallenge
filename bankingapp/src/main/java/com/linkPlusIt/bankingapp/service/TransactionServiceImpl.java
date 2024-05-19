package com.linkPlusIt.bankingapp.service;

import com.linkPlusIt.bankingapp.dto.DepositRequestDTO;
import com.linkPlusIt.bankingapp.dto.TransactionDTO;
import com.linkPlusIt.bankingapp.dto.TransferRequestDTO;
import com.linkPlusIt.bankingapp.dto.WithdrawalRequestDTO;
import com.linkPlusIt.bankingapp.model.Account;
import com.linkPlusIt.bankingapp.model.Transaction;
import com.linkPlusIt.bankingapp.repository.AccountRepository;
import com.linkPlusIt.bankingapp.repository.BankRepository;
import com.linkPlusIt.bankingapp.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final BigDecimal FLAT_FEE = new BigDecimal("10.00");
    private static final BigDecimal PERCENT_FEE = new BigDecimal("0.05");

    @Override
    public TransactionDTO transferFunds(TransferRequestDTO request) {
        Account fromAccount = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Originating account not found"));
        Account toAccount = accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

        BigDecimal feeAmount = request.getIsFlatFee() ? FLAT_FEE : request.getAmount().multiply(PERCENT_FEE);
        BigDecimal totalAmountToDeduct = request.getAmount().add(feeAmount);
        if (fromAccount.getBalance().compareTo(totalAmountToDeduct) < 0) {
            throw new IllegalStateException("Insufficient funds");
        }

        // Update the balances of the originating and resulting accounts
        fromAccount.setBalance(fromAccount.getBalance().subtract(totalAmountToDeduct));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        // Save all entities
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Create and save the transaction
        Transaction transaction = createTransaction(request, fromAccount, toAccount, feeAmount);
        transaction = transactionRepository.save(transaction);

        return modelMapper.map(transaction, TransactionDTO.class);
    }

    @Override
    public List<TransactionDTO> getAccountTransactions(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByOriginatingAccountId(accountId);
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO depositFunds(DepositRequestDTO request) {
        Account toAccount = accountRepository.findById(request.getAccountId()).orElseThrow(() -> new RuntimeException("Destination account not found"));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        Transaction transaction = new Transaction(null,
                request.getAmount(),
                request.getAccountId(),
                null,
                request.getReason(),
                BigDecimal.ZERO,
                false);

        accountRepository.save(toAccount);

        transaction = transactionRepository.save(transaction);
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    @Override
    public TransactionDTO withdrawFunds(WithdrawalRequestDTO request) {
        Account fromAccount = accountRepository.findById(request.getAccountId()).orElseThrow(() -> new RuntimeException("Originating account not found"));
        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        Transaction transaction = new Transaction(null,
                request.getAmount(),
                request.getAccountId(),
                null,
                request.getReason(), BigDecimal.ZERO, false);

        accountRepository.save(fromAccount);

        transaction = transactionRepository.save(transaction);
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    private static Transaction createTransaction(TransferRequestDTO request, Account fromAccount, Account toAccount, BigDecimal feeAmount) {
        Transaction transaction = new Transaction();
        transaction.setOriginatingAccountId(fromAccount.getId());
        transaction.setResultingAccountId(toAccount.getId());
        transaction.setAmount(request.getAmount());
        transaction.setTransactionReason("Transfer");
        transaction.setFlatFee(request.getIsFlatFee());
        transaction.setFeeAmount(feeAmount);
        return transaction;
    }
}
