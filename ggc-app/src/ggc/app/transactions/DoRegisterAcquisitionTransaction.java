package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import ggc.WarehouseManager;
import ggc.AcquisitionProduct;
import ggc.app.exceptions.*;
import java.util.*;
import java.io.*;

/**
 * Register order.
 */
public class DoRegisterAcquisitionTransaction extends Command<WarehouseManager> {

  /**
   * Constructor.
   * @param receiver
   */
  public DoRegisterAcquisitionTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_ACQUISITION_TRANSACTION, receiver);
    addStringField("partnerKey", Prompt.partnerKey());
    addStringField("productKey", Prompt.productKey());
    addIntegerField("productPrice", Prompt.price());
    addIntegerField("productQuantity", Prompt.amount());
  }

  /**
   * @see pt.tecnico.uilib.menus.Command#execute()
   * @throws CommandException
   */
  @Override
  public final void execute() throws CommandException {
    String partnerID = stringField("partnerKey");
    String productID = stringField("productKey");
    Integer productPrice = integerField("productPrice");
    Integer productQuantity = integerField("productQuantity");
    List<AcquisitionProduct> _products = new ArrayList<AcquisitionProduct>();
    if (_receiver.getPartner(partnerID) == null) {
      throw new UnknownPartnerKeyException(partnerID);
    }
    if (_receiver.getProduct(productID) == null) {
      String answer = Form.requestString(Prompt.addRecipe());
      if (answer.equals("sim")) {
      }
      
      
      throw new UnknownProductKeyException(productID);
    }

  }

}
