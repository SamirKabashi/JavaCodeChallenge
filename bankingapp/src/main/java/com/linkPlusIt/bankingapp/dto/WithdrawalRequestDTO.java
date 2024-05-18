package com.linkPlusIt.bankingapp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawalRequestDTO {

    private Long accountId;
    private BigDecimal amount;
    private String reason;
}
