package com.example.bankingapp.service;

import com.example.bankingapp.dao.AccountDAO;
import com.example.bankingapp.dao.TransactionDAO;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankingServiceImpl implements BankingService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    @Transactional
    public void transfer(String fromAcc, String toAcc, Double amount) {
        // fetch accounts
        Account src = accountDAO.findByAccountNumber(fromAcc);
        Account dst = accountDAO.findByAccountNumber(toAcc);

        if (src == null) {
            throw new RuntimeException("Source account not found: " + fromAcc);
        }
        if (dst == null) {
            throw new RuntimeException("Destination account not found: " + toAcc);
        }
        if (src.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in account: " + fromAcc);
        }

        // debit & credit
        src.setBalance(src.getBalance() - amount);
        dst.setBalance(dst.getBalance() + amount);

        // persist updates
        accountDAO.update(src);
        accountDAO.update(dst);

        // record transaction
        TransactionEntity txn = new TransactionEntity(fromAcc, toAcc, amount);
        transactionDAO.save(txn);

        // if you want to simulate error to test rollback, uncomment:
        // if (true) throw new RuntimeException("Simulated failure after DB updates");
    }
}
