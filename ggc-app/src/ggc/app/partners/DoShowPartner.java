package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.*;

/**
 * Show partner.
 */
class DoShowPartner extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  DoShowPartner(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER, receiver);
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
    _display.addLine(_receiver.getPartner(id).toString());
    for (var notification : _receiver.getPartnerNotifications(id)) {
      _display.addLine(notification.toString());
    }
    _display.display();
  }

}
