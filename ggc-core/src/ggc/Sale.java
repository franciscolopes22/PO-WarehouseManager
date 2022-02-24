package ggc;

/**
 * Class Sale is a subclass of a Transaction and it's instantiated
 * when the Warehouse sells products to partners.
 */
public class Sale extends Transaction {
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* Partner who made the sale. */
    private Partner _partner;

    /* Product sold to Partner. */
    private Product _product;

    /* Deadline to pay sale. */
    private int _deadline;

    /* Amount of Product demanded by Partner. */
    private int _productQuantity;

    /* Total paid sale price. */
    private double _paidPrice;

    /**
     * Create sale.
     * @param id
     * @param Partner
     * @param product
     * @param deadline
     * @param amount
     */
    public Sale(int transactionID, Partner Partner, Product product, int deadline, int quantity) {
        super(transactionID);
        _partner = Partner;
        _product = product; 
        _deadline = deadline;
        _productQuantity = quantity;
    }

    /**
     * @return the sale's total price (after taxes).
     * the value returned from this funtion depends on the Partner status
     * and the date where the payment was made.
     */
    public double getTotalPrice() {
        int N = _product.getN();
        int paymentGap = getLimitDateGap(); 
        if (getPaymentStatus() == true) {return _paidPrice;}
        if (paymentGap >= 0) {
            if (paymentGap >= N) {
                return _partner.getStatus().p1Limiter() * getBasePrice();
            } else {
                return _partner.getStatus().p2Limiter(paymentGap) * getBasePrice();
            }
        } else {
            paymentGap *= -1;
            if (paymentGap <= N && 0 < paymentGap) {
                return _partner.getStatus().p3Limiter(-1*paymentGap) * getBasePrice();
            } else {
                return _partner.getStatus().p4Limiter(-1*paymentGap) * getBasePrice();
            }
        }
    }

    /**
     * @return the sale's quantiy of products
     */
    public int getProductQuantity() {
        return _productQuantity;
    }

    /**
     * @return the sale's partner.
     */
    public Partner getPartner() {
        return _partner;
    }

    /**
     * @return the difference between deadline payment date and warehouse's current date
     */
    public int getLimitDateGap() {
        return _deadline - getCurrentWarehouseDate();
    }

    /**
     * @return the sale's product.
     */
    public Product getProduct() {
        return _product;
    }

    /**
     * @return the sale's limit payment date
     */
    public int getDeadline() {
        return _deadline;
    }

    /**
     * Updates sale paidPrice and paymentDate and makes Partner pay the sale.
     */
    @Override
    public void pay() {
        _paidPrice = getTotalPrice();
        setPaymentDate(getCurrentWarehouseDate());
        _partner.paySale(this);
    }

    @Override
    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return getID() + "|" + getPartner().getID() + "|" + getProduct().getID() + "|" + 
        getProductQuantity() + "|" + getBasePrice() + "|" + (int) Math.round(getTotalPrice()) + "|" + getDeadline()
        + (getPaymentStatus() == true ? "|" + getPaymentDate() : "");
    }

}
