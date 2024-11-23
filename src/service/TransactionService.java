/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import core.models.Account;
import core.models.Transaction;
import bank.TransactionType;
import java.util.ArrayList;


/**
 *
 * @author corre
 */
public class TransactionService {

    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public Transaction performTransaction(TransactionType type, Account sourceAccount, Account destinationAccount, double amount) {
        if (type == TransactionType.TRANSFER && destinationAccount == null) {
            throw new IllegalArgumentException("Las transferencias requieren una cuenta de destino.");
        }

        // Validaciones de saldo
        if (type == TransactionType.WITHDRAW && sourceAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Saldo insuficiente para el retiro.");
        }
        if (type == TransactionType.TRANSFER && sourceAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Saldo insuficiente para la transferencia.");
        }

        
        if (null != type) switch (type) {
            case DEPOSIT:
                sourceAccount.deposit(amount);
                break;
            case WITHDRAW:
                sourceAccount.withdraw(amount);
                break;
            case TRANSFER:
                sourceAccount.withdraw(amount);
                destinationAccount.deposit(amount);
                break;
            default:
                break;
        }

        // Crear la transacción
        Transaction transaction = new Transaction(
            type,
            sourceAccount.getId(),
            destinationAccount != null ? destinationAccount.getId() : null,
            amount
        );
        transactions.add(transaction);
        return transaction;
    }

    public ArrayList<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    public ArrayList<Transaction> getTransactionsByAccount(String accountId) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (accountId.equals(t.getSourceAccountId()) || accountId.equals(t.getDestinationAccountId())) {
                result.add(t);
            }
        }
        return result;
    }
}
