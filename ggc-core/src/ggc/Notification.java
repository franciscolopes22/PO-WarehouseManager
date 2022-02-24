package ggc;

import java.io.*;

public class Notification implements Serializable {
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* Notification delivery mode name. */
    private String _deliveryMode;

    /* Notification type (New or Bargain) */
    private String _type;

    /* Notification's product ID. */
    private String _productID;

    /* Notification's price. */
    private int _price;

    /* create a notification */
    public Notification(String deliveryMode, String type, String productID, int price) {
        _deliveryMode = deliveryMode;
        _type = type;
        _productID = productID;
        _price = price;
    }

    /**
     * @return notification's product ID.
     */
    public String getProductID() {
        return _productID;
    }

    /**
     * @return notification's delivery mode name.
     */
    public String getDeliveryModeName() {
        return _deliveryMode;
    }

    /**
     * @return notification's product price.
     */
    public int getProductPrice() {
        return _price;
    }

    /**
     * @return notification's type.
     */
    public String getType() {
        return _type;
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return getDeliveryModeName() + getType() + "|" + getProductID() + "|" + getProductPrice();
    }

}
