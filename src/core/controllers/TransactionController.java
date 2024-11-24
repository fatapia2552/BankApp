/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.utils.TransactionType;
import core.models.Account;
import core.models.Transaction;
import core.models.storage.AccountsStorage;
import core.models.storage.TransactionsStorage;

/**
 *
 * @author Yuceth
 */
public class TransactionController {

    public static Response Execute(String operationType, String sourceacc, String destacc, String amount) {
        try {
            double amountInt;

            // Validación general del monto
            try {
                amountInt = Double.parseDouble(amount);
                if (amountInt <= 0) {
                    return new Response("Amount must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }

            // Inicialización de almacenamiento
            AccountsStorage accstorage = AccountsStorage.getInstance();
            TransactionsStorage transtorage = TransactionsStorage.getInstance();

            // Lógica según la operación
            switch (operationType.toUpperCase()) {
                case "DEPOSIT":
                    if (destacc == null || destacc.isEmpty()) {
                        return new Response("Destination Account must not be empty", Status.BAD_REQUEST);
                    }
                    Account destinationAccount = accstorage.getAccount(destacc);
                    if (destinationAccount == null) {
                        return new Response("Destination Account does not exist", Status.NOT_FOUND);
                    }

                    destinationAccount.deposit(amountInt);
                    if (!transtorage.addTransaction(new Transaction(TransactionType.DEPOSIT, null, destinationAccount, amountInt))) {
                        return new Response("Transaction could not be recorded", Status.NOT_IMPLEMENTED);
                    }
                    return new Response("Transaction completed successfully", Status.CREATED);

                case "WITHDRAW":
                    if (sourceacc == null || sourceacc.isEmpty()) {
                        return new Response("Source Account must not be empty", Status.BAD_REQUEST);
                    }
                    Account sourceAccount = accstorage.getAccount(sourceacc);
                    if (sourceAccount == null) {
                        return new Response("Source Account does not exist", Status.NOT_FOUND);
                    }
                    if (sourceAccount.getBalance() < amountInt) {
                        return new Response("Insufficient funds", Status.BAD_REQUEST);
                    }

                    sourceAccount.withdraw(amountInt);
                    if (!transtorage.addTransaction(new Transaction(TransactionType.WITHDRAW, sourceAccount, null, amountInt))) {
                        return new Response("Transaction could not be recorded", Status.NOT_IMPLEMENTED);
                    }
                    return new Response("Transaction completed successfully", Status.CREATED);

                case "TRANSFER":
                    if (sourceacc == null || sourceacc.isEmpty() || destacc == null || destacc.isEmpty()) {
                        return new Response("Source and Destination Accounts must not be empty", Status.BAD_REQUEST);
                    }
                    Account srcAccount = accstorage.getAccount(sourceacc);
                    Account destAccount = accstorage.getAccount(destacc);
                    if (srcAccount == null) {
                        return new Response("Source Account does not exist", Status.NOT_FOUND);
                    }
                    if (destAccount == null) {
                        return new Response("Destination Account does not exist", Status.NOT_FOUND);
                    }
                    if (srcAccount.getBalance() < amountInt) {
                        return new Response("Insufficient funds in Source Account", Status.BAD_REQUEST);
                    }

                    srcAccount.withdraw(amountInt);
                    destAccount.deposit(amountInt);
                    if (!transtorage.addTransaction(new Transaction(TransactionType.TRANSFER, srcAccount, destAccount, amountInt))) {
                        return new Response("Transaction could not be recorded", Status.NOT_IMPLEMENTED);
                    }
                    return new Response("Transaction completed successfully", Status.CREATED);

                default:
                    return new Response("Invalid operation type", Status.BAD_REQUEST);
            }

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
   
    }