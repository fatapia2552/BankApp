/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

/**
 *
 * @author edangulo
 */
public class Account {

    private String id;
    private int ownerId; 
    private double balance;

    public Account(String id, Integer ownerId) {
        this.id = id;
        this.ownerId = ownerId;
        this.balance = 0;
    }

    public Account(String id, Integer ownerId, double balance) {
        this.id = id;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto depositado debe ser mayor a cero.");
        }
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto retirado debe ser mayor a cero.");
        }
        if (amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        return true;
    }
}
