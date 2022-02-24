package ggc;

import java.io.*;

public interface ProductObserver extends Serializable {
    /**
     * Adds a notification to the array of Partner notifications.
     */
    void addNotification(String type, String productID, int price);
    
}
