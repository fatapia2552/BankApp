/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.storage.AccountsStorage;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yuceth
 */
public class AccountListController {
    
        public static Response Listar(DefaultTableModel table) {
           try { 
            AccountsStorage storage = AccountsStorage.getInstance();
            if (storage.getAccounts().isEmpty()) {
                return new Response("List empty", Status.NO_CONTENT);
            }
            
            for(Account account : storage.getAccounts()) {
                table.addRow(new Object[]{account.getId(), account.getOwner().getId(), account.getBalance()});
            }
            }catch(Exception ex) {
                   return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR); 
            }
           return new Response("List completed", Status.OK);
        }

}
