/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author Yuceth
 */
public class UsersStorage {

    
    private static UsersStorage instance;
    
    private ArrayList<User> users;

    private UsersStorage() {
        this.users = new ArrayList<>();
    }
    
    
    
    public static UsersStorage getInstance() {
    if(instance == null) {
        instance = new UsersStorage();
    }
    return instance;
}
    public boolean addUser(User user) {
        for (User u : this.users) {
            if(u.getId() == user.getId()) {
                return false;
            }
        }
        this.users.add(user);
        return true;
    }
    
    public User getUser(int id) {
        for (User user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
}

