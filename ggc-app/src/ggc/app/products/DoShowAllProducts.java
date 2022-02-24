package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;

/**
 * Show all products.
 */
class DoShowAllProducts extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  DoShowAllProducts(WarehouseManager receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public final void execute() throws CommandException {
    for (var product: _receiver.getProducts()) {
      _display.addLine(_receiver.getProductString(product));
    }
    _display.display();
  }

}
