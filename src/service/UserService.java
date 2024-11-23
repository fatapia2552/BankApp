/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import core.models.Account;
import core.models.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author corre
 */
public class UserService {

    private final Map<Integer, User> users = new HashMap<>();
    private final Map<Integer, ArrayList<Account>> userAccounts = new HashMap<>(); 

    public void registerUser(int id, String firstname, String lastname, int age) {
        if (users.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe un usuario con este ID.");
        }
        User user = new User(id, firstname, lastname, age);
        users.put(id, user);
        userAccounts.put(id, new ArrayList<>()); 
    }

    public User getUserById(int id) {
        User user = users.get(id);
        if (user == null) {
            throw new IllegalArgumentException("El usuario no existe.");
        }
        return user;
    }

    public ArrayList<Account> getUserAccounts(int userId) {
        if (!userAccounts.containsKey(userId)) {
            throw new IllegalArgumentException("El usuario no existe.");
        }
        return new ArrayList<>(userAccounts.get(userId));
    }

    public void addAccountToUser(int userId, Account account) {
        if (!userAccounts.containsKey(userId)) {
            throw new IllegalArgumentException("El usuario no existe.");
        }
        userAccounts.get(userId).add(account);
    }


}
