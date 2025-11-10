package com.example.bankingapp.service;

public interface BankingService {
    void transfer(String fromAcc, String toAcc, Double amount);
}
