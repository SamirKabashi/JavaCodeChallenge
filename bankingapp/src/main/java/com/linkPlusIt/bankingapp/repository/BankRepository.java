package com.linkPlusIt.bankingapp.repository;

import com.linkPlusIt.bankingapp.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}
