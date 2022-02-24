package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * 
 */
public class DoRegisterSaleTransaction extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  public DoRegisterSaleTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
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
