package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.*;

/**
 * Toggle product-related notifications.
 */
class DoToggleProductNotifications extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  DoToggleProductNotifications(WarehouseManager receiver) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, receiver);
    addStringField("partnerKey", Prompt.partnerKey());
    addStringField("productKey", Prompt.productKey());
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override 
  public void execute() throws CommandException {
    String partnerID = stringField("partnerKey");
    String productID = stringField("productKey");
    if (_receiver.getPartner(partnerID) == null) {
      throw new UnknownPartnerKeyException(partnerID);
    }
    if (_receiver.getProduct(productID) == null) {
      throw new UnknownProductKeyException(productID);
    }
    _receiver.toggleNotifications(partnerID, productID);
  }

}