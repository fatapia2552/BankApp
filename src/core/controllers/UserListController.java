/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.AccountsStorage;
import core.models.storage.UsersStorage;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yuceth
 */
public class UserListController {
    
    public static Response Listar(DefaultTableModel table) {
           try { 
            UsersStorage storage = UsersStorage.getInstance();
            if (storage.getUsers().isEmpty()) {
                return new Response("List empty", Status.NO_CONTENT);
            }
            for(User user : storage.getUsers()) {
                table.addRow(new Object[]{user.getId(), user.getFirstname() + " " + user.getLastname(), user.getAge(), user.getNumAccounts()});
            }
            }catch(Exception ex) {
                   return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR); 
            }
           return new Response("List completed", Status.OK);
        }
    
    
    
}
