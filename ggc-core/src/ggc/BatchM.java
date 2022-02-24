package ggc;

import java.util.*;
import java.io.*;

/**
 * This class is used to represent a batch with a derivative product. A batch of a derivative
 * product has a product ID, a partner ID, the price of the product, the current stock 
 * of the product, the aggravation value and the recipe.
 */
public class BatchM extends Batch {
    
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* Batch's derivative product aggravation */
    private double _aggravation;

    /* Batch's derivative product recipe */
    private String _recipe;

    /**
     * Create a Batch.
     * @param productID batch's product ID
     * @param partnerID batch's partner ID
     * @param productPrice batch's product price
     * @param currentStock batch's product stock
     * @param aggravation batch's derivative product aggravation value
     * @param recipe batch's derivative product recipe
     * 
     */
    BatchM(String productID, String partnerID, double productPrice, int currentStock, double aggravation, String recipe) {
        super(productID, partnerID, productPrice, currentStock);
        _aggravation = aggravation;
        _recipe = recipe;
    }

    /**
     * @return the batch's derivative product aggravation value.
     */
    public double getAggravation() { return _aggravation; }

    /**
     * @return the batch's derivative product recipe.
     */
    public String getRecipe() { return _recipe; }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return getProductID() + "|" + getPartnerID() + "|" + getProductPrice() + "|" + getCurrentStock(); 
    }

}
