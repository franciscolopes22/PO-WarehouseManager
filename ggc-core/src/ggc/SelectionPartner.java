package ggc;

public class SelectionPartner extends PartnerStatus {
    
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* creates the partner with a Selection Status */
    public SelectionPartner(Partner partner) {
        super(partner);
    }

    /* Selection Partner P2 limiter discount */
    @Override
    public double p2Limiter(int gap) {
        if (gap >= 2) {
            return 0.95;
        }
        return 1.0;
    }

    /* Selection Partner P3 limiter discount */
    @Override
    public double p3Limiter(int gap) {
        if (gap > 1) {
            return 1.0 - 0.02*gap;
        }
        return 1.0;
    }

    /* Selection Partner P4 limiter discount */
    @Override
    public double p4Limiter(int gap) {
        return 1.0 - 0.05*gap;
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "SELECTION";
    }

}
