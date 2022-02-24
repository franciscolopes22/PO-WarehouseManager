package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;


/**
 * Show available batches.
 */
class DoShowAvailableBatches extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  DoShowAvailableBatches(WarehouseManager receiver) {
    super(Label.SHOW_AVAILABLE_BATCHES, receiver);
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public final void execute() throws CommandException {
    for (var batch: _receiver.getBatchesOrdered()) {
      _display.addLine(batch.toString());
    }
    _display.display();
  }

}
