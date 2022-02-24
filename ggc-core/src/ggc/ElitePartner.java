package ggc;

public class ElitePartner extends PartnerStatus {
    
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* creates the partner with an Elite Status */
    public ElitePartner(Partner partner) {
        super(partner);
    }

    /* Elite Partner P2 limiter discount */
    @Override
    public double p2Limiter(int gap) {
        return 0.9;
    }

    /* Elite Partner P3 limiter discount */
    @Override
    public double p3Limiter(int gap) {
        return 0.95;
    }

    /* Elite Partner P4 limiter discount */
    @Override
    public double p4Limiter(int gap) {
        return 1.0;
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "ELITE";
    }

}
