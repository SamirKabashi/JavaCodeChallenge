package com.linkPlusIt.bankingapp.service;

import com.linkPlusIt.bankingapp.dto.AccountDTO;
import com.linkPlusIt.bankingapp.dto.BankDTO;
import com.linkPlusIt.bankingapp.model.Bank;
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
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BankDTO createBank(BankDTO bankDTO) {
        Bank bank = modelMapper.map(bankDTO, Bank.class);
        bank = bankRepository.save(bank);
        return modelMapper.map(bank, BankDTO.class);
    }

    @Override
    public List<BankDTO> getAllBanks() {
        List<Bank> banks = bankRepository.findAll();
        return banks.stream()
                .map(bank -> modelMapper.map(bank, BankDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalTransactionFeeAmount(Long bankId) {
        return bankRepository.findById(bankId)
                .map(Bank::getTotalTransactionFeeAmount)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal getTotalTransferAmount(Long bankId) {
        return bankRepository.findById(bankId)
                .map(Bank::getTotalTransferAmount)
                .orElse(BigDecimal.ZERO);
    }

    public List<AccountDTO> findAccountsByBankId(Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        return bank.getAccounts().stream()
                .map(account -> new AccountDTO(
                        account.getId(),
                        account.getUserName(),
                        account.getBalance(),
                        account.getBank().getId()
                ))
                .collect(Collectors.toList());
    }
}
