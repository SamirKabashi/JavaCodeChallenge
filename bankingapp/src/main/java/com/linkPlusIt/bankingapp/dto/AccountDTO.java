package com.linkPlusIt.bankingapp.dto;

import com.linkPlusIt.bankingapp.model.Bank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    private String userName;
    private BigDecimal balance;
    private Long bankId;
}
