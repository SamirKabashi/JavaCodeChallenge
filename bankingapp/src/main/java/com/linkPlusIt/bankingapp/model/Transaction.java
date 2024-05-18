package com.linkPlusIt.bankingapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "originating_account_id", nullable = false)
    private Long originatingAccountId;

    @Column(name = "resulting_account_id")
    private Long resultingAccountId;

    @Column(name = "transaction_reason")
    private String transactionReason;

    @Column(name = "fee_amount")
    private BigDecimal feeAmount;

    @Column(name = "is_flat_fee")
    private boolean isFlatFee;
}
