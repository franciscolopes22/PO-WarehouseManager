package ggc;

import java.io.*;

public interface TransactionVisitor extends Serializable {
    /* visits an acquisition */
    void visit(Acquisition acquisition);
    /* visits a sale */
    void visit(Sale sale);
    /* visits a breakdown */
    void visit(Breakdown breakdown);
}
