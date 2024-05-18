package com.linkPlusIt.bankingapp.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDTO {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private boolean isFlatFee;
    private BigDecimal feeValue;
    private String reason;

    public boolean getIsFlatFee() {
        return isFlatFee;
    }

}