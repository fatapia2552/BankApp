/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Transaction;
import java.util.ArrayList;

/**
 *
 * @author Yuceth
 */
public class TransactionsStorage {
    private static TransactionsStorage instance;
    
    private ArrayList<Transaction> transactions;

    private TransactionsStorage() {
        this.transactions = new ArrayList<>();
    }
    
    
    
    public static TransactionsStorage getInstance() {
    if(instance == null) {
        instance = new TransactionsStorage();
    }
    return instance;
}
    
    public boolean addTransaction(Transaction transaction) {
        
        this.transactions.add(transaction);
        return true;
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
