package ggc;
import java.io.*;

public interface ObservableProduct extends Serializable {
    
    /**
     * Registers an Observer (Partner) to a Product.
     */
    void registerObserver(ProductObserver observer);

    /**
     * Removes an Observer (Partner) from a Product.
     */
    void removeObserver(ProductObserver observer);

    /**
     * Notify all observers when the Observable's state has changed.
     */
    void notifyObservers(String type);
}
