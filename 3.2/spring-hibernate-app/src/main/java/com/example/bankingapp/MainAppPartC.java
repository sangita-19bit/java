package com.example.bankingapp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.bankingapp.config.AppConfig;
import com.example.bankingapp.dao.AccountDAO;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.service.BankingService;

public class MainAppPartC {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        AccountDAO accountDAO = ctx.getBean(AccountDAO.class);
        BankingService bankingService = ctx.getBean(BankingService.class);

        // Initialize two accounts if they do not exist
        ctx.getBeanFactory().getBeanNamesForType(Account.class); // just to initialize context

        // For simplicity create accounts if not present - using session-bound operations:
        ctx.getBeanFactory().getBean(AccountDAO.class); // ensure bean created

        // create accounts in a transactional block via bankingService? We'll use plain DAO but need current session transaction.
        // Simpler: use a small transaction template via a service -- but to keep code minimal, we can:
        try {
            // if accounts absent, create them via a simple transaction using Spring's session (start one)
            org.springframework.transaction.PlatformTransactionManager tm = ctx.getBean(org.springframework.transaction.PlatformTransactionManager.class);
            org.springframework.transaction.TransactionDefinition def = new org.springframework.transaction.support.DefaultTransactionDefinition();
            org.springframework.transaction.TransactionStatus status = tm.getTransaction(def);

            try {
                Account a1 = accountDAO.findByAccountNumber("ACC1001");
                if (a1 == null) {
                    a1 = new Account("ACC1001", 10000.0);
                    accountDAO.save(a1);
                }
                Account a2 = accountDAO.findByAccountNumber("ACC2002");
                if (a2 == null) {
                    a2 = new Account("ACC2002", 2000.0);
                    accountDAO.save(a2);
                }
                tm.commit(status);
            } catch (Exception ex) {
                tm.rollback(status);
                throw ex;
            }

            System.out.println("Before transfer:");
            System.out.println(accountDAO.findByAccountNumber("ACC1001"));
            System.out.println(accountDAO.findByAccountNumber("ACC2002"));

            // Transfer money
            bankingService.transfer("ACC1001", "ACC2002", 1500.0);

            System.out.println("After transfer:");
            System.out.println(accountDAO.findByAccountNumber("ACC1001"));
            System.out.println(accountDAO.findByAccountNumber("ACC2002"));

            // To test rollback uncomment the exception in service to simulate failure.

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            ctx.close();
        }
    }
}
