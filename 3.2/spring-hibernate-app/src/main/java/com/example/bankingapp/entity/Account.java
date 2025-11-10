package com.example.bankingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private Double balance;

    public Account() { }

    public Account(String accountNumber, Double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Account{id=" + id + ", accountNumber='" + accountNumber + "', balance=" + balance + "}";
    }
}
