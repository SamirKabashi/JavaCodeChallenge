package com.linkPlusIt.bankingapp.config;

import com.linkPlusIt.bankingapp.dto.AccountDTO;
import com.linkPlusIt.bankingapp.dto.BankDTO;
import com.linkPlusIt.bankingapp.dto.TransactionDTO;
import com.linkPlusIt.bankingapp.model.Account;
import com.linkPlusIt.bankingapp.model.Bank;
import com.linkPlusIt.bankingapp.model.Transaction;
import com.linkPlusIt.bankingapp.repository.BankRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    private final BankRepository bankRepository;

    // Constructor injection of the BankRepository
    public ModelMapperConfig(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Account to AccountDTO
        modelMapper.typeMap(Account.class, AccountDTO.class).addMappings(mapper -> {
            mapper.map(Account::getId, AccountDTO::setId);
            mapper.map(Account::getUserName, AccountDTO::setUserName);
            mapper.map(Account::getBalance, AccountDTO::setBalance);
        });

        Converter<Long, Bank> toBankConverter = new AbstractConverter<>() {
            protected Bank convert(Long source) {
                return source == null ? null : bankRepository.findById(source).orElse(null);
            }
        };
        modelMapper.addConverter(toBankConverter, Long.class, Bank.class);
        modelMapper.typeMap(AccountDTO.class, Account.class).addMappings(mapper ->
                mapper.using(toBankConverter).map(AccountDTO::getBankId, Account::setBank));

        // Transaction to TransactionDTO
        modelMapper.typeMap(Transaction.class, TransactionDTO.class).addMappings(mapper -> {
            mapper.map(Transaction::getId, TransactionDTO::setId);
            mapper.map(Transaction::getAmount, TransactionDTO::setAmount);
            mapper.map(Transaction::getOriginatingAccountId, TransactionDTO::setOriginatingAccountId);
            mapper.map(Transaction::getResultingAccountId, TransactionDTO::setResultingAccountId);
            mapper.map(Transaction::getTransactionReason, TransactionDTO::setTransactionReason);
            mapper.map(Transaction::isFlatFee, TransactionDTO::setFlatFee);
            mapper.map(Transaction::getFeeAmount, TransactionDTO::setFeeAmount);
        });

        // Bank to BankDTO
        modelMapper.typeMap(Bank.class, BankDTO.class).addMappings(mapper -> {
            mapper.map(Bank::getId, BankDTO::setId);
            mapper.map(Bank::getName, BankDTO::setName);
            mapper.map(src -> src.getAccounts() != null ? src.getAccounts().stream()
                            .map(account -> new AccountDTO(
                                    account.getId(),
                                    account.getUserName(),
                                    account.getBalance(),
                                    account.getBank().getId()))
                            .collect(Collectors.toList()) : new ArrayList<AccountDTO>(),
                    BankDTO::setAccounts);
            mapper.map(Bank::getTotalTransactionFeeAmount, BankDTO::setTotalTransactionFeeAmount);
            mapper.map(Bank::getTotalTransferAmount, BankDTO::setTotalTransferAmount);
            mapper.map(Bank::getTransactionFlatFeeAmount, BankDTO::setTransactionFlatFeeAmount);
            mapper.map(Bank::getTransactionPercentFeeValue, BankDTO::setTransactionPercentFeeValue);
        });

        return modelMapper;
    }
}
