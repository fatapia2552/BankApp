/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;


/**
 *
 * @author edangulo
 */

public class Transaction {

    
    private TransactionType type;
    private String sourceAccountId; 
    private String destinationAccountId; 
    private double amount;

    public Transaction(TransactionType type, String sourceAccountId, String destinationAccountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto de la transacción debe ser mayor a cero.");
        }
        
        this.type = type;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
    }



    public TransactionType getType() {
        return type;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction [type=" + type + ", sourceAccountId=" + sourceAccountId +
               ", destinationAccountId=" + destinationAccountId + ", amount=" + amount + "]";
    }
}