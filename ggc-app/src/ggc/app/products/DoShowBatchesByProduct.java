package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.*;

/**
 * Show all products.
 */
class DoShowBatchesByProduct extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  DoShowBatchesByProduct(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_BY_PRODUCT, receiver);
    addStringField("productKey", Prompt.productKey());
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public final void execute() throws CommandException {
    String id = stringField("productKey");
    if (_receiver.getProduct(id) == null) {
      throw new UnknownProductKeyException(id);
    }
    for (var batch: _receiver.getBatches()) {
      if (batch.getProductID().equals(id)) {
        _display.addLine(batch.toString());
      }
    }
    _display.display();
  }

}
