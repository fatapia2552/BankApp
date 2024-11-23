/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import bank.Account;
import bank.User;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author corre
 */
public class AccountService {

    private final Map<String, Account> accounts = new HashMap<>();
    private final UserService userService; 

    public AccountService(UserService userService) {
        this.userService = userService;
    }

    public Account createAccount(String accountId, User user) {
        if (accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Ya existe una cuenta con este ID.");
        }
        Account account = new Account(accountId, user.getId());
        accounts.put(accountId, account);
        userService.addAccountToUser(user.getId(), account);
        return account;
    }

    public Account getAccountById(String accountId) {
        return accounts.get(accountId);
    }
}
