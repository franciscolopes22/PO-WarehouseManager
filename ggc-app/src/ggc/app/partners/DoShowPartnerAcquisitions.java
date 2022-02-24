package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.*;

/**
 * Show all transactions for a specific partner.
 */
class DoShowPartnerAcquisitions extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  DoShowPartnerAcquisitions(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER_ACQUISITIONS, receiver);
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
    for (var acquisition : _receiver.getPartnerAcquisitions(id)) {
      _display.addLine(acquisition.toString());
    }
    _display.display();
  }

}
