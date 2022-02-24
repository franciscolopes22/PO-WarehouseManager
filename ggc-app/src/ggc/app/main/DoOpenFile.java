package ggc.app.main;

import java.io.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.*;
import ggc.exceptions.*;

/**
 * Open existing saved state.
 */
class DoOpenFile extends Command<WarehouseManager> {

  /**
   * @param receiver
   */
  DoOpenFile(WarehouseManager receiver) {
    super(Label.OPEN, receiver);
    addStringField("filenameToOpen", Prompt.openFile());
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public final void execute() throws CommandException {
    String filename = stringField("filenameToOpen");
    try {
      _receiver.load(filename);
    } catch (UnavailableFileException | IOException | ClassNotFoundException e) {
      throw new FileOpenFailedException(filename);
    }
  }
}
