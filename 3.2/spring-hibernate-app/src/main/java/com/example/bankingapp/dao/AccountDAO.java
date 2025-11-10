package com.example.bankingapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.bankingapp.entity.Account;

@Repository
public class AccountDAO {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Account findByAccountNumber(String accNo) {
        return getCurrentSession()
                .createQuery("from Account where accountNumber = :acc", Account.class)
                .setParameter("acc", accNo)
                .uniqueResult();
    }

    public void update(Account account) {
        getCurrentSession().update(account);
    }

    public void save(Account account) {
        getCurrentSession().save(account);
    }
}
