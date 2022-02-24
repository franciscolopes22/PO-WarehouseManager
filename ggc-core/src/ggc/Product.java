package ggc;

import java.util.*;
import java.io.*;

/**
 * This class is used to represent a product. A product has an ID.
 */
public class Product implements ObservableProduct {
    
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* Product's id. */
    private String _id;

    private int _price;

    /** Array containing observers who want to be notified about this product's events. */
    private Map<ProductObserver, Boolean> _observers = new HashMap<ProductObserver, Boolean>();


    /**
     * Create a product.
     * @param id product's id.
     */
    public Product(String id) {
        _id = id;
    }

    /**
     * @return the product's id.
     */
    public String getID() {
        return _id;
    }

    /**
     * @return the product's type.
     */
    public String getType() {
        return "SIMPLE";
    }

    /**
     * @return the product's aggravation.
     */
    public double getAggravation(){
        return 0.0;
    }

    /**
     * @return the simple product's period variable.
     */
    public int getN() {
        return 5;
    }

    /**
     * @return the product's aggravation.
     */
    public String getRecipeString(){
        return "s";
    }
    
    /**
     * Returns all Observers interested in this product as an unmodifiable map.
     */
    public Map<ProductObserver, Boolean> getObservers() {
        return Collections.unmodifiableMap(_observers);
    }

    /**
     * Notifies Observers about a given event.
     */
    @Override
    public void notifyObservers(String event) {
        for (Map.Entry<ProductObserver, Boolean> observer : _observers.entrySet()) {
            if (observer.getValue() == true) {
                observer.getKey().addNotification(event, _id, _price);
            }
        }
    }

    /**
     * Registers an Observer to be notified about events to this product.
     */
    @Override
    public void registerObserver(ProductObserver observer) {
        _observers.put(observer, true);
    }

    /**
     * Removes an Observer as interested about this product's events.
     */
    @Override
    public void removeObserver(ProductObserver observer) {
        _observers.put(observer, false);
    }


}
