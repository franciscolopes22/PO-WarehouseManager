package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import ggc.WarehouseManager;
import ggc.app.exceptions.*;
import ggc.exceptions.*;
import java.io.*;

/**
 * Save current state to file (if there's already a filename, overwrite it, else, ask for it).
 */
class DoSaveFile extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public final void execute() throws CommandException {
    try {
      if (_receiver.getFilename() == null) {
        String s = Form.requestString(Prompt.newSaveAs());
        _receiver.saveAs(s);
      } else {
        _receiver.save();
      }
    } catch (MissingFileAssociationException | IOException e) {
      e.printStackTrace();
    }
  }

}
