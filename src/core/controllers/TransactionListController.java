/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Transaction;
import core.models.storage.TransactionsStorage;

     

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author corre
 */
public class TransactionListController {
    
        public static Response Listar(DefaultTableModel table) {
           try { 
            TransactionsStorage storage = TransactionsStorage.getInstance();
            if (storage.getTransactions().isEmpty()) {
                return new Response("List empty", Status.NO_CONTENT);
            }

            for(Transaction transaction : storage.getTransactions()) {
                table.addRow(new Object[]{transaction.getType().name(), transaction.getSourceAccount() != null ? transaction.getSourceAccount().getId() : "N/A", transaction.getDestinationAccount() != null ? transaction.getDestinationAccount().getId() : "N/A", transaction.getAmount()});

            }
            }catch(Exception ex) {
                   return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR); 
            }
           return new Response("List completed", Status.OK);
        }
}
