package com.linkPlusIt.bankingapp.service;

import com.linkPlusIt.bankingapp.dto.AccountDTO;
import com.linkPlusIt.bankingapp.dto.BankDTO;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {

    BankDTO createBank(BankDTO bank);
    List<BankDTO> getAllBanks();
    List<AccountDTO> findAccountsByBankId(Long bankId);
    BigDecimal getTotalTransactionFeeAmount(Long bankId);
    BigDecimal getTotalTransferAmount(Long bankId);
}
