/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.models.User;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.UsersStorage;

/**
 *
 * @author Yuceth
 */
public class UserControllers {
    public static Response RegisterUser(String id, String firstname, String lastname, String age){
        try {
            int IdInt, AgeInt;
            try {
                IdInt = Integer.parseInt(id);
                if (IdInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
                if (IdInt> 999999999) {
                    return new Response("Id can't have more than 9 digits", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            if(firstname.equals("")) {
                return new Response("First name must not be empty", Status.BAD_REQUEST);
            }
            if(lastname.equals("")) {
                return new Response("Last name must not be empty", Status.BAD_REQUEST);
            }
            try {
                AgeInt = Integer.parseInt(age);
                if (AgeInt < 18) {
                    return new Response("User must be over 18 years old", Status.BAD_REQUEST);
                }
                
            }catch(NumberFormatException ex) {
                return new Response("Age must be numeric", Status.BAD_REQUEST);
            }
            UsersStorage storage = UsersStorage.getInstance();
            if (!storage.addUser(new User(IdInt, firstname, lastname, AgeInt))) {
                return new Response("An user with that ID already exists", Status.BAD_REQUEST);
            }
            return new Response("User registered succesfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR); 
        }
    }
}
