package ggc;

public class WarehouseVisitor implements TransactionVisitor {

    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* Warehouse's initial balance. */
    private float _totalBalance = 0;

    /**
     * Visit an acquisition and decrease Warehouse's total balance.
     * @param acquisition
     */
    @Override
	public void visit(Acquisition acquisition) {
        _totalBalance -= acquisition.getBasePrice();
	}

    /**
     * Visit a sale and increase Warehouse's total balance.
     * @param sale sale being visited.
     */
	@Override
	public void visit(Sale sale) {
        _totalBalance += sale.getTotalPrice();
    }

    /**
     * Visit a breakdown and increase Warehouse's total balance.
     * @param breakdown breakdown being visited.
     */
	@Override
	public void visit(Breakdown breakdown) {
        _totalBalance += breakdown.getTotalPrice();
    }

    /**
     * Returns Warehouse's total balance rounded to nearest integer.
     * @return Warehouse's total balance.
     */
    public int getTotalBalance() {
        return Math.round(_totalBalance);
    }
    
}
