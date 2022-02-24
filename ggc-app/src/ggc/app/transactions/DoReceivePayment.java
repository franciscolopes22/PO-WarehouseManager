package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.*;

/**
 * Receive payment for sale transaction.
 */
public class DoReceivePayment extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  public DoReceivePayment(WarehouseManager receiver) {
    super(Label.RECEIVE_PAYMENT, receiver);
    addIntegerField("transactionKey", Prompt.transactionKey());
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public final void execute() throws CommandException {
    int id = integerField("transactionKey");
    if (_receiver.getTransaction(id) == null) {
      throw new UnknownTransactionKeyException(id);
    }
    _receiver.pay(id);
  }

}
