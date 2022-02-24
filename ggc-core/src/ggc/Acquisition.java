package ggc;
import java.util.*;

public class Acquisition extends Transaction {

    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* An acquisition's partner's ID. */
    private String _partnerID;
    
    /* A map of acquisition products */
    private List<AcquisitionProduct> _products = new ArrayList<AcquisitionProduct>();

    /**
     * Create acquisition transaction.
     */
    public Acquisition(int transactionID, String partnerID) {
        super(transactionID);
        _partnerID = partnerID;
    }

    /**
     * @return the acquisition's Partner's ID.
     */
    public String getPartnerID() {
        return _partnerID;
    }

    /**
     * Adds a product to acquisition.
     */
    public void addProduct(AcquisitionProduct product) {
        _products.add(product);
    }

    /**
     * @return a Map containing all acquisition products and its quantities.
     */
    public Collection<AcquisitionProduct> getAcquisitionProducts() {
        return Collections.unmodifiableCollection(_products);
    }

    /**
     * Empty method because acquisitions are instantly paid.
     */
    @Override
    public void pay() {
    }

    /**
     * Accepts a concrete transaction visitor.
     */
    @Override
    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String base = getID() + "|" + getPartnerID() + "|" + getBasePrice() + "|" + getPaymentDate();
        for (AcquisitionProduct product : getAcquisitionProducts()) {
            base += '\n' + product.getProductID() + "|" + product.getProductQuantity();
        }
        return base;
    }
}
