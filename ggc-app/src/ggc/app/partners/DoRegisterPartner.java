package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.*;

/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);
    addStringField("partnerKey", Prompt.partnerKey());
    addStringField("partnerName", Prompt.partnerName());
    addStringField("partnerAddress", Prompt.partnerAddress());
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public void execute() throws CommandException {
    String id = stringField("partnerKey");
    String name = stringField("partnerName");
    String address = stringField("partnerAddress");
    if (_receiver.getPartner(id) != null) {
      throw new DuplicatePartnerKeyException(id);
    }
    _receiver.registerPartner(id, name, address);
  }

}
