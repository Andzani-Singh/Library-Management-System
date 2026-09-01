package com.mycompany.lms.service;

import com.mycompany.lms.dao.TransactionDO;
import com.mycompany.lms.model.Transaction;
import java.util.List;

public class TransactionService {
    private TransactionDO transactionDO = new TransactionDO();
    
    public Transaction saveTransaction(Transaction transaction) {
        return transactionDO.save(transaction);
    }
    
    public Transaction getTransactionById(Long id) {
        return transactionDO.findById(id);
    }
    
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionDO.findByUserId(userId);
    }
    
    public List<Transaction> getAllTransactions() {
        return transactionDO.findAll();
    }
    
    public void deleteTransaction(Long id) {
        transactionDO.delete(id);
    }
}
