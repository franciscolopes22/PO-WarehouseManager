package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  public DoShowTransaction(WarehouseManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    addIntegerField("transactionKey", Prompt.transactionKey());
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public final void execute() throws CommandException {
      _display.popup(_receiver.getTransaction(integerField("transactionKey")));
  }

}
