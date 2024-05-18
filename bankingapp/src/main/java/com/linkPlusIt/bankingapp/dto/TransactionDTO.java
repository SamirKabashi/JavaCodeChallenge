package com.linkPlusIt.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private BigDecimal amount;
    private Long originatingAccountId;
    private Long resultingAccountId;
    private String transactionReason;
    private BigDecimal feeAmount;
    private boolean isFlatFee;


}
