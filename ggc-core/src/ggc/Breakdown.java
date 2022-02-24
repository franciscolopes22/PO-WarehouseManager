package ggc;

public class Breakdown extends Transaction {
    
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;
    
    private Partner _partner;

    private Product _product;
    
    /**
     * Create a breakdown transaction.
     * @param transactionID
     * @param partner
     * @param product
     */
    public Breakdown(int transactionID, Partner partner, Product product) {
        super(transactionID);
        _partner = partner;
        _product = product;
    }

    /**
     * @return the breakdown's total price (after taxes).
     * the value returned from this funtion depends on the Partner status
     * and the date where the payment was made.
     */
    public double getTotalPrice() {
        return 0.0;
    }

    @Override
    public void pay() {
    }

    @Override
    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }

}
