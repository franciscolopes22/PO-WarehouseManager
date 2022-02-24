package ggc;

import java.io.*;

public abstract class Transaction implements TransactionVisitable {
    
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;
    
    /* Transaction's ID. */
    private int _transactionID;

    /* Transaction's payment date. */
    private int _paymentDate;

    /* Transaction's base price (before taxes). */
    private double _basePrice = 0;

    /* Transaction's paid status */
    private boolean _isPaid = false;

    /* Warehouse's current date */
    private int _warehouseDate;

    /**
     * Create new Transaction.
     * @param id
     */
    public Transaction(int transactionID) {
        _transactionID=transactionID;
    }

    /**
     * @return the transaction's unique ID
     */
    public int getID() {
        return _transactionID;
    }

    /**
     * Returns transaction paid status.
     * 
     * @return true if transaction is paid; false, otherwise.
     */
    public boolean getPaymentStatus() {
        return _isPaid;
    }

    /**
     * Sets transaction payment date and transaction as paid.
     * Called upon warehouse acquisition or partner sale payment.
     * @param date
     */
    public void setPaymentDate(int date) {
        _paymentDate = date;
        _isPaid = true;
    }

    /**
     * @return the transaction's payment date.
     */
    public int getPaymentDate() {
        return _paymentDate;
    }
    
   /**
     * Updates transaction with current Warehouse date.
     * @param date
     */
    public void setCurrentWarehouseDate(int date) {
        _warehouseDate = date;
    }

    /**
     * Returns last saved Warehouse date on transcation.
     * @return transaction's last Warehouse date record.
     */
    public int getCurrentWarehouseDate() {
        return _warehouseDate;
    }

    /**
     * @return the transaction's base price (before taxes).
     */
    public double getBasePrice() {
        return _basePrice;
    }

    /**
     * Sets transaction's base price (before taxes).
     */
    public void setBasePrice(double basePrice) {
        _basePrice = basePrice;
    }

    /**
     * Pays the transaction.
     */
    public abstract void pay();

}
