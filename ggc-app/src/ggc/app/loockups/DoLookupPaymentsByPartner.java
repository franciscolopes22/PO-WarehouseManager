package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.*;

/**
 * Lookup payments by given partner.
 */
public class DoLookupPaymentsByPartner extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  public DoLookupPaymentsByPartner(WarehouseManager receiver) {
    super(Label.PAID_BY_PARTNER, receiver);
    addStringField("partnerKey", Prompt.partnerKey());
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public void execute() throws CommandException {
    String id = stringField("partnerKey");
    if (_receiver.getPartner(id) == null) {
      throw new UnknownPartnerKeyException(id);
    }
    /*
    for (var partner : _receiver.getPartners()) {
      _display.addLine(partner.toString());
    }
    _display.display();
    */
  }

}
