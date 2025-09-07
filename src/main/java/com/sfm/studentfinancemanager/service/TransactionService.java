package com.sfm.studentfinancemanager.service;

import com.sfm.studentfinancemanager.model.Transaction;
import com.sfm.studentfinancemanager.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction createTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    public List<Transaction> getTransactionsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

}
