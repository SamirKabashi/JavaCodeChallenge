package com.linkPlusIt.bankingapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();;

    @Column(name = "total_transaction_fee_amount", precision = 19, scale = 2)
    private BigDecimal totalTransactionFeeAmount;

    @Column(name = "total_transfer_amount", precision = 19, scale = 2)
    private BigDecimal totalTransferAmount;

    @Column(name = "transaction_flat_fee_amount", precision = 19, scale = 2)
    private BigDecimal transactionFlatFeeAmount;

    @Column(name = "transaction_percent_fee_value", precision = 19, scale = 2)
    private BigDecimal transactionPercentFeeValue;
}
