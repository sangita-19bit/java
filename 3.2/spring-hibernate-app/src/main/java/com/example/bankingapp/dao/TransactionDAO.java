package com.example.bankingapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.bankingapp.entity.TransactionEntity;

@Repository
public class TransactionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(TransactionEntity txn) {
        getCurrentSession().save(txn);
    }
}
