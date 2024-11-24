/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.utils.TransactionType;
import core.models.Account;
import core.models.Transaction;
import core.models.storage.AccountsStorage;
import core.models.storage.TransactionsStorage;

/**
 *
 * @author Yuceth
 */
public class TransactionController {
    
    public static Response Deposit(String destacc, String amount) {
        try {
            double amountInt;
            AccountsStorage accstorage = AccountsStorage.getInstance();
            Account destinationAccount = accstorage.getAccount(destacc);
            if(destacc.equals("")){
                return new Response("Destination Account must not be empty", Status.BAD_REQUEST);
            }
            
            try {
                amountInt = Double.parseDouble(amount);
                
                if(amountInt < 0) {
                    return new Response("Amount must be positive", Status.BAD_REQUEST);
                }
               
                
            }catch(NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }
            if (destinationAccount != null) {
                        destinationAccount.deposit(amountInt);
            }
            TransactionsStorage transtorage = TransactionsStorage.getInstance();
            if(!transtorage.addTransaction(new Transaction(TransactionType.DEPOSIT, null, destinationAccount, amountInt))){
                return new Response("", Status.NOT_IMPLEMENTED);
            } 
            return new Response("Transaction completed succesfully", Status.CREATED);
            }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR); 
        }
    }
    
    
    
    public static Response Withdraw(String sourceacc, String amount) {
        
    }
    
    
    
    public static Response Transfer(String sourceacc, String destacc, String amount) {
        
    }
}
