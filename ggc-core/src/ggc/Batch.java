package ggc;

import java.util.*;
import java.io.*;

/**
 * This class is used to represent a batch with a simple product (and also acts as the 
 * superclass for the batch with a derivative product (BatchM.java)). A batch of a simple
 * product has a product ID, a partner ID, the price of the product and the current stock 
 * of the product. 
 */
public class Batch implements Serializable {
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* Batch's product ID */
    private String _productID;

    /* Batch's partner ID */
    private String _partnerID;

    /* Batch's product price */
    private double _productPrice;

    /* Batch's product stock */
    private int _currentStock;

    /**
     * Create a Batch.
     * @param productID batch's product ID
     * @param partnerID batch's partner ID
     * @param productPrice batch's product price
     * @param currentStock batch's product stock
     */
    Batch(String productID, String partnerID, double productPrice, int currentStock) {
        _productID = productID;
        _partnerID = partnerID;
        _productPrice = productPrice;
        _currentStock = currentStock;
    }

    /**
     * @return the batch's product ID.
     */
    public String getProductID() { return _productID; }

    /**
     * @return the batch's partner ID.
     */
    public String getPartnerID() { return _partnerID; }

    /**
     * @return the batch's product price.
     */
    public double getProductPrice() { return _productPrice; }

    /**
     * @return the batch's product stock.
     */
    public int getCurrentStock() { return _currentStock; }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return getProductID() + "|" + getPartnerID() + "|" + Math.round(getProductPrice()) + "|" + getCurrentStock(); 
    }

}
