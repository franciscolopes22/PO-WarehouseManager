package ggc;

public class NormalPartner extends PartnerStatus {
    
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* creates the partner with a Normal Status */
    public NormalPartner(Partner partner) {
        super(partner);
    }

    /* Normal Partner P2 limiter discount */
    @Override
    public double p2Limiter(int gap) {
        return 1.0;
    }

    /* Normal Partner P3 limiter discount */
    @Override
    public double p3Limiter(int gap) {
        return 1.0 - 0.05*gap;
    }

    /* Normal Partner P4 limiter discount */
    @Override
    public double p4Limiter(int gap) {
        return 1.0 - 0.1*gap;
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "NORMAL";
    }
}
