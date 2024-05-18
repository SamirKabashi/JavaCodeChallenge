package com.linkPlusIt.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {

    private Long id;
    private String name;
    private List<AccountDTO> accounts;
    private BigDecimal totalTransactionFeeAmount;
    private BigDecimal totalTransferAmount;
    private BigDecimal transactionFlatFeeAmount;
    private BigDecimal transactionPercentFeeValue;
}
