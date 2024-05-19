package com.linkPlusIt.bankingapp.service;

import com.linkPlusIt.bankingapp.dto.AccountDTO;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    AccountDTO createAccount(AccountDTO account);
    BigDecimal getAccountBalance(Long accountId);
}
