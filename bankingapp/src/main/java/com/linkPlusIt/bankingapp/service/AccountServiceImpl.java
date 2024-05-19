package com.linkPlusIt.bankingapp.service;

import com.linkPlusIt.bankingapp.dto.AccountDTO;
import com.linkPlusIt.bankingapp.model.Account;
import com.linkPlusIt.bankingapp.model.Bank;
import com.linkPlusIt.bankingapp.repository.AccountRepository;
import com.linkPlusIt.bankingapp.repository.BankRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
        account = accountRepository.save(account);
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public BigDecimal getAccountBalance(Long accountId) {
        return accountRepository.findById(accountId)
                .map(Account::getBalance)
                .orElse(BigDecimal.ZERO);
    }
}
