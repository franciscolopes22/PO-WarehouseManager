package ggc;
import java.io.*;

public class AcquisitionProduct implements Serializable {
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* An acquisition's product's ID. */
    private String _productID;

    /* An acquisition's product's qty. */
    private int _productQuantity;

    /**
     * Constructor.
     * @param productID
     * @param productQty
     */
    public AcquisitionProduct(String productID, int productQuantity) {
        _productID = productID;
        _productQuantity = productQuantity;
    }

    /**
     * Returns a product ID associated to an acquisition.
     * @return an acquisition's product's ID.
     */
    public String getProductID() {
        return _productID;
    }

    /**
     * Returns a product quantity associated to an acquisition.
     * @return an acquisition's product's quantity.
     */
    public int getProductQuantity() {
        return _productQuantity;
    }
    
    /**
     * Updates a product's ID.
     * @param pID
     */
    public void setProductID(String productID) {
        _productID = productID;
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return _productID + "|" + _productQuantity;
    }
}
