/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;



/**
 *
 * @author edangulo
 */
public class User {

    private int id;
    private String firstname;
    private String lastname;
    private int age;

    public User(int id, String firstname, String lastname, int age) {
        if (age < 18) {
            throw new IllegalArgumentException("El usuario debe ser mayor de edad.");
        }
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname + " (ID: " + id + ")";
    }
}
