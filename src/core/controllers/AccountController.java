/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.User;
import core.models.storage.AccountsStorage;
import core.models.storage.UsersStorage;
import java.util.Random;

/**
 *
 * @author Yuceth
 */
public class AccountController {
 public static Response CreateAccount(String id, String balance) {
     try {
            int userId;
            double initialBalance;
            try {
                userId = Integer.parseInt(id);
              
                if (userId < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
                if (userId> 999999999) {
                    return new Response("Id can't have more than 9 digits", Status.BAD_REQUEST);
                }
                
            }catch(NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            User selectedUser = null;
                UsersStorage ustorage = UsersStorage.getInstance();
                selectedUser = ustorage.getUser(userId);
                
                if (selectedUser == null) {
                    return new Response("No user with that ID is registered", Status.NOT_FOUND);
                }
                
                AccountsStorage storage = AccountsStorage.getInstance();
                boolean verifyId = false;
                
                while(verifyId == false){
                Random random = new Random();
                int first = random.nextInt(1000);
                int second = random.nextInt(1000000);
                int third = random.nextInt(100);
                
                String accountId = String.format("%03d", first) + "-" + 
                                   String.format("%06d", second) + "-" + 
                                   String.format("%02d", third);
                
                if(storage.getAccount(accountId) == null) {
                    verifyId = true;
                }
                }
                try {
                    initialBalance = Double.parseDouble(balance);
                    
                    if(initialBalance < 0) {
                        return new Response("Balance must be positive", Status.BAD_REQUEST);
                    }
                }catch(NumberFormatException ex) {
                return new Response("Balance must be numeric", Status.BAD_REQUEST);
            }
                
                if (!storage.addAccount(new Account(id, selectedUser.getId(), initialBalance))) {
                return new Response("An Account with that User already exists", Status.BAD_REQUEST);
            }
                return new Response("Account succesfully created", Status.CREATED);
            
                
             
     }
            catch (Exception ex) {
                     return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
                     }
        }
    }
  

