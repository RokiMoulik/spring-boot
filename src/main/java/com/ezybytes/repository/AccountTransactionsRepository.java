package com.ezybytes.repository;

import com.ezybytes.model.AccountTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransaction, Long> {
    List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(int customerId);
}
