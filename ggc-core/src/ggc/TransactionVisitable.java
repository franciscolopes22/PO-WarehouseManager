package ggc;

import java.io.*;

public interface TransactionVisitable extends Serializable {
    /**
     * Accepts the visitor.
     * @param visitor
     */
    void accept(TransactionVisitor visitor);
}
