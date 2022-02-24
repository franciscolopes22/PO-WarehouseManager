package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;

/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductBatchesUnderGivenPrice extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  public DoLookupProductBatchesUnderGivenPrice(WarehouseManager receiver) {
    super(Label.PRODUCTS_UNDER_PRICE, receiver);
    addStringField("priceLimit", Prompt.priceLimit());
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public void execute() throws CommandException {
    double priceLimit = Double.parseDouble(stringField("priceLimit"));
    for (var batch: _receiver.getBatchesOrdered()) {
      if (batch.getProductPrice() < priceLimit) {
        _display.addLine(batch.toString());
      }
    }
    _display.display();
  }

}
