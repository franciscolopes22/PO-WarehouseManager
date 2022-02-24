package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.*;

/**
 * Advance current date.
 */
class DoAdvanceDate extends Command<WarehouseManager> {
  
  /**
   * @param receiver
   */
  DoAdvanceDate(WarehouseManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    addIntegerField("daysToAdvance", Prompt.daysToAdvance());
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   * @throws InvalidDateException
   */
  @Override
  public final void execute() throws CommandException, InvalidDateException {
    int days = integerField("daysToAdvance");
    if (days <= 0) {
      throw new InvalidDateException(days);
    }
    _receiver.advanceDate(days);
  }

}
