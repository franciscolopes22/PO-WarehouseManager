package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * Register order.
 */
public class DoRegisterBreakdownTransaction extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  public DoRegisterBreakdownTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_BREAKDOWN_TRANSACTION, receiver);
    //FIXME maybe add command fields
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public final void execute() throws CommandException {
    //FIXME implement command
  }

}
