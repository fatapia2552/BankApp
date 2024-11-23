/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import java.util.ArrayList;

/**
 *
 * @author Yuceth
 */
public class AccountsStorage {
    private static AccountsStorage instance;
    
    private ArrayList<Account> accounts;

    private AccountsStorage() {
        this.accounts = new ArrayList<>();
    }
    
    
    
    public static AccountsStorage getInstance() {
    if(instance == null) {
        instance = new AccountsStorage();
    }
    return instance;
}
    public boolean addAccount(Account account) {
        for (Account a : this.accounts) {
            if(a.getId() == account.getId()) {
                return false;
            }
        }
        this.accounts.add(account);
        return true;
    }
}