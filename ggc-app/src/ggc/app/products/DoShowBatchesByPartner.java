package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.*;

/**
 * Show batches supplied by partner.
 */
class DoShowBatchesByPartner extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  DoShowBatchesByPartner(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_SUPPLIED_BY_PARTNER, receiver);
    addStringField("partnerKey", Prompt.partnerKey());
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public final void execute() throws CommandException {
    String id = stringField("partnerKey");
    if (_receiver.getPartner(id) == null) {
      throw new UnknownPartnerKeyException(id);
    }
    for (var batch: _receiver.getBatches()) {
      if (batch.getPartnerID().equals(id)) {
        _display.addLine(batch.toString());
      }
    }
    _display.display();
  }
}
