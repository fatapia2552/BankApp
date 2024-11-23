/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import bank.User;
import java.util.ArrayList;

/**
 *
 * @author Yuceth
 */
public class UsersStorage {

    
    private static UsersStorage instance;
    
    private ArrayList<User> persons;

    private UsersStorage() {
        this.persons = new ArrayList<>();
    }
    
    
    
    public static UsersStorage getInstance() {
    if(instance == null) {
        instance = new UsersStorage();
    }
    return instance;
}
    public boolean addUser(User user) {
        for (User u : this.persons) {
            if(u.getId() == user.getId()) {
                return false;
            }
        }
        this.persons.add(user);
        return true;
    }
    
    public User getPerson(int id) {
        for (User user : this.persons) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}

