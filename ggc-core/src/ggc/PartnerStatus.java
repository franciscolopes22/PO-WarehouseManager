package ggc;

import java.io.*;

public abstract class PartnerStatus implements Serializable {
    
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* Partner associated with the respective status */
    private Partner _partner;

    /* creates the partner status */
    public PartnerStatus(Partner partner) {
        _partner = partner;
    }

    /* Period P1 discount */
    public double p1Limiter() {
        return 0.9;
    };

    /* Period P2 discount */
    public abstract double p2Limiter(int gap);

    /* Period P3 discount */
    public abstract double p3Limiter(int gap);

    /* Period P4 discount */
    public abstract double p4Limiter(int gap);

}
