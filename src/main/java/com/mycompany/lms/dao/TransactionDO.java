package com.mycompany.lms.dao;

import com.mycompany.lms.model.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TransactionDO {
    private static final ConcurrentHashMap<Long, Transaction> transactions = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);
    
    public Transaction save(Transaction transaction) {
        if (transaction.getId() == null) {
            transaction.setId(idGenerator.getAndIncrement());
        }
        transactions.put(transaction.getId(), transaction);
        return transaction;
    }
    
    public Transaction findById(Long id) {
        return transactions.get(id);
    }
    
    public List<Transaction> findByUserId(Long userId) {
        List<Transaction> results = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.getUser().getId().equals(userId)) {
                results.add(transaction);
            }
        }
        return results;
    }
    
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions.values());
    }
    
    public void delete(Long id) {
        transactions.remove(id);
    }
}
