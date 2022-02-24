package ggc;

import java.io.*;
import ggc.exceptions.*;
import java.util.*;

/**
 * WarehouseManager: façade for the core classes.
 */
public class WarehouseManager {

  /* Filename of the current stored Warehouse. */
  private String _filename;

  /* The warehouse itself. */
  private Warehouse _warehouse = new Warehouse();

  /* Variable to check if the warehouse has been modified since the previous save. */
  private boolean _save = true;

   /**
   * @return the WarehouseManager's save filename.
   */
  public String getFilename() {
    return _filename;
  }

  /**
   * @return the WarehouseManager's save variable state.
   */
  public boolean getSave() {
    return _save;
  }

  /**
   * Saves current Warehouse data in the WarehouseManager associated filename.
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    try {
      if (_save == true) {
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
        out.writeObject(_warehouse);
        out.close();
        _save = false;
      }
    } catch(IOException e) {
      throw new MissingFileAssociationException();
    }
  }

  /**
   * Sets WarehouseManager filename and saves current Warehouse.
   * @@param filename
   * @@throws MissingFileAssociationException
   * @@throws IOException
   * @@throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @param filename
   * @throws UnavailableFileEIOExceptionxception
   * @throws UnavailableFileException
   * @throws FileNotFoundException
   * @throws ClassNotFoundException
   */
  public void load(String filename) throws IOException, UnavailableFileException, FileNotFoundException, ClassNotFoundException {
    try {
      ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
      _warehouse = (Warehouse) in.readObject();
      in.close();
      _filename = filename;
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException(filename);
    }
  }

  /*###########################################################*/
  /*----------------------- IMPORT FILE -----------------------*/
  /*###########################################################*/

  /**
   * @param textfile textfile to import
   */
  public void importFile(String textfile) throws ImportFileException {
    if (textfile != null) {
      _warehouse.importFile(textfile);
    } else {
      throw new ImportFileException(textfile);
    }
  }

  /*#####################################################*/
  /*----------------------- DATE -----------------------*/
  /*#####################################################*/

  /**
   * Gets the Warehouse's current date.
   * @return Warehouse current date.
   */
  public int getDate() {
    return _warehouse.getDate();
  }

  /**
   * Advances warehouse's date.
   * @param days
   */
  public void advanceDate(int days){
    _save = true;
    _warehouse.advanceDate(days);
  }
  
  /*#####################################################*/
  /*----------------------- BALANCE ---------------------*/
  /*#####################################################*/

  /**
   * Returns warehouse's available balance.
   * @return warehouse available balance.
   */
  public int getAvailableBalance() {
    return _warehouse.getAvailableBalance();
  }

  /**
   * Returns warehouse's accounting balance.
   * @return warehouse accounting balance.
   */
  public int getAccountingBalance() {
    return _warehouse.getAccountingBalance();
  }


  /*#####################################################*/
  /*----------------------- PRODUCT ---------------------*/
  /*#####################################################*/

  /**
   * @param id product's id.
   * @return Product with the corresponding ID.
   */
  public Product getProduct(String productID) {
    return _warehouse.getProduct(productID);
  }

  /**
   * Gets a string with all the products information (either simple or
   * derivative product).
   * @param product
   * @return string that has all the product's info.
   */
  public String getProductString(Product product) {
    return _warehouse.getProductString(product);
  }

  /**
   * @return Warehouse's products.
   */
  public Collection<Product> getProducts() {
    return _warehouse.getProducts();
  }

  /*#####################################################*/
  /*----------------------- BATCH ---------------------*/
  /*#####################################################*/

  /**
   * @return Warehouse's batches.
   */
  public Collection<Batch> getBatches() {
    return _warehouse.getBatches();
  }

  /**
   * @return Warehouse's batches ordered by product price first and then by
   * current stock, when the batch's product ID and partner ID are equal.
   */
  public Collection<Batch> getBatchesOrdered() {
    return _warehouse.getBatchesOrdered();
  } 

  /**
   * Register a new batch (with a simple product)
   * @param productID batch's product ID.
   * @param partnerID batch's partner ID.
   * @param price batch's product price.
   * @param currentStock batch's product stock.
   */
  public void registerBatchS(String productID, String partnerID, double price, int currentStock) {
    _save = true;
    _warehouse.registerBatchS(productID, partnerID, price, currentStock);
    
  }

  /**
   * Register a new batch (with a derivative product)
   * @param productID batch's product ID.
   * @param partnerID batch's partner ID.
   * @param price batch's product price.
   * @param currentStock batch's product stock.
   * @param aggravation batch's product aggravation.
   * @param recipe batch's product recipe.
   * 
   */
  public void registerBatchM(String productID, String partnerID, double price, int currentStock, double aggravation, String recipe) {
    _save = true;
    _warehouse.registerBatchM(productID, partnerID, price, currentStock, aggravation, recipe);
  }

  /*#####################################################*/
  /*----------------------- TRANSACTION ----------------*/
  /*#####################################################*/

  /**
   * @param id transaction id.
   * @return transaction with the corresponding id.
   */
  public Transaction getTransaction(int transactionID) {
    return _warehouse.getTransaction(transactionID);
  }

  /**
   * Pays a transaction given its unique ID.
   * @param transactionID
   */
  public void pay(int transactionID) {
    _save = true;
    _warehouse.pay(transactionID);
  }

  /**
   * Registers a warehouse sale.
   * @param partnerID
   * @param deadline
   * @param productID
   * @param productQuantity
   */
  public void registerSale(String partnerID, int deadline, String productID, int productQuantity) {
    _save = true;
    _warehouse.registerSale(partnerID, deadline, productID, productQuantity);
  }

  /**
   * Registers a warehouse acquisition.
   * @param partnerID
   * @param products
   */
  public void registerAcquisition(String partnerID, Collection<AcquisitionProduct> products) {
    _save = true;
    _warehouse.registerAcquisition(partnerID, products);
  }

  /*#####################################################*/
  /*----------------------- PARTNER ---------------------*/
  /*#####################################################*/

  /**
   * @param id partner id.
   * @return partner with the corresponding id.
   */
  public Partner getPartner(String id) {
    return _warehouse.getPartner(id);
  }

  /**
   * @return Warehouse's partners.
   */
  public Collection<Partner> getPartners() {
    return _warehouse.getPartners();
  }

  /**
   * Register a new partner
   * @param id partner ID.
   * @param name partner name.
   * @param address partner address.
   */
  public void registerPartner(String id, String name, String address) {
    _save = true;
    _warehouse.registerPartner(id, name, address);
  }

  /**
   * Returns a partner's acquisitions as an unmodifiable collection.
   * @param partnerID
   * @return a collection with all partner's notifications.
   */
  public Collection<Acquisition> getPartnerAcquisitions(String partnerID) {
    return _warehouse.getPartnerAcquisitions(partnerID);
  }

  /*#####################################################*/
  /*----------------------- NOTIFICATIONS----------------*/
  /*#####################################################*/

  /**
   * Returns a partner's notifications as an unmodifiable collection.
   * @param partnerID
   * @return a collection with all partner's notifications.
   */
  public Collection<Notification> getPartnerNotifications(String partnerID) {
    return _warehouse.getPartnerNotifications(partnerID);
  } 

  /**
   * Toggle's partner's notification for a specific product.
   */
  public void toggleNotifications(String partnerID, String productID) {
    _save = true;
    _warehouse.toggleNotifications(partnerID,productID);
  }

}
