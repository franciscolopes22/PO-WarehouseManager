package ggc;

import java.io.*;
import java.util.*;
import ggc.exceptions.*;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /* Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  /* Current date. */
  private int _date = 0;

  /* Next transaction ID. */
  private int _transactionID = 0;

  /* Next batch ID (important when later removing batches with empty stock).*/
  private int _batchID = 0;
  
  /* Warehouse partners. */
  private Map<String, Partner> _partners = new TreeMap<String, Partner>(String.CASE_INSENSITIVE_ORDER);

  /* Warehouse products. */
  private Map<String, Product> _products = new TreeMap<String, Product>(String.CASE_INSENSITIVE_ORDER);

  /* Warehouse transactions. */
  private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();

  /* Warehouse batches. */
  private Map<Integer, Batch> _batches = new TreeMap<Integer, Batch>();


  /*###########################################################*/
  /*----------------------- IMPORT FILE -----------------------*/
  /*###########################################################*/

  /**
   * @param txtfile filename to be imported.
   * @throws FileNotFoundException
   * @throws IOException
   * @throws BadEntryException
   */

  void importFile(String txtfile) {
    try (BufferedReader in = new BufferedReader(new FileReader(txtfile))) {
      String s;
      while ((s = in.readLine()) != null) {
        String[] args = s.split("\\|");
        switch (args[0]) {
        case "PARTNER" -> registerPartner(args[1],args[2],args[3]);
        case "BATCH_S" -> registerBatchS(args[1],args[2],Double.parseDouble(args[3]),Integer.parseInt(args[4]));
        default -> throw new BadEntryException(args[0]);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (BadEntryException e) {
      e.printStackTrace();
    }
  }

  /*#####################################################*/
  /*----------------------- DATE -----------------------*/
  /*#####################################################*/

  /**
   * @return Warehouse's current date.
   */
  public int getDate() {
    return _date;
  }

  /**
   * Updates de Warehouse date.
   * @param days days to advance.
   */
  public void advanceDate(int days) {
    _date += days;
  }

  /*#####################################################*/
  /*----------------------- BALANCE ---------------------*/
  /*#####################################################*/

  /**
   * Returns Warehouse's available balance.
   * @return Warehouse available balance.
   */
  public int getAvailableBalance() {
    WarehouseVisitor visitor = new WarehouseVisitor();
    for (Transaction transaction : _transactions.values()) {
      if (transaction.getPaymentStatus() == true) {
        transaction.accept(visitor);
      }
    }
    return visitor.getTotalBalance();
  }

  /**
   * Returns Warehouse's accounting balance.
   * @return Warehouse accounting balance.
   */
  public int getAccountingBalance() {
    WarehouseVisitor visitor = new WarehouseVisitor();
    for (Transaction transaction : _transactions.values()) {
      transaction.setCurrentWarehouseDate(_date);
      transaction.accept(visitor);
    }
    return visitor.getTotalBalance();
  }



  /*#####################################################*/
  /*----------------------- PRODUCT ---------------------*/
  /*#####################################################*/

  /**
   * @param id product's id.
   * @return Product with the corresponding ID.
   */
  public Product getProduct(String id) {
    return _products.get(id);
  }

  /**
   * Gets a string with all the products information (either simple or
   * derivative product).
   * @param product
   * @return string that has all the product's info.
   */
  public String getProductString(Product product) {
    double maxPrice = 0.0;
    int totalStock = 0;
    for (var batch: _batches.values()) {
      if (batch.getProductID().equals(product.getID())) {
        maxPrice = Math.max(maxPrice, batch.getProductPrice());
        totalStock += batch.getCurrentStock();
      }
    }
    String line = product.getID() + "|" + Math.round(maxPrice) + "|" + totalStock;
    
    if (product.getType() == "DERIVATIVE") {
      line += (product.getAggravation() + "|" + product.getRecipeString());
    }

    return line;
  }

  /**
   * @return Warehouse's products.
   */
  public Collection<Product> getProducts() {
    return Collections.unmodifiableCollection(_products.values());
  }

  /*#####################################################*/
  /*----------------------- BATCH ---------------------*/
  /*#####################################################*/

  /**
   * @return Warehouse's batches.
   */
  public Collection<Batch> getBatches() {
    return Collections.unmodifiableCollection(_batches.values());
  }

  /**
   * @return Warehouse's batches ordered by product price first and then by
   * current stock, when the batch's product ID and partner ID are equal.
   */
  public Collection<Batch> getBatchesOrdered() {
    Comparator<Batch> byProduct = Comparator.comparing(Batch::getProductID);
    Comparator<Batch> byPartner = Comparator.comparing(Batch::getPartnerID);
    Comparator<Batch> byPrice = Comparator.comparing(Batch::getProductPrice);
    Comparator<Batch> byStock = Comparator.comparing(Batch::getCurrentStock);
    List<Batch> orderedBatches = new ArrayList<Batch>(_batches.values());
    Collections.sort(orderedBatches, byProduct.thenComparing(byPartner).thenComparing(byPrice).thenComparing(byStock));
    return Collections.unmodifiableCollection(orderedBatches);
  }

  /**
   * Register a new batch (with a simple product)
   * @param productID batch's product ID.
   * @param partnerID batch's partner ID.
   * @param price batch's product price.
   * @param currentStock batch's product stock.
   */
  public void registerBatchS(String productID, String partnerID, double price, int currentStock) {
    Batch newBatch = new Batch(productID, partnerID, price, currentStock);
    Product product = getProduct(productID);
    if (product==null) {
      Product newProduct = new Product(productID);
      _products.put(productID, newProduct);
    }
    _batches.put(_batchID++, newBatch);
    
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
    Batch newBatch = new BatchM(productID, partnerID, price, currentStock, aggravation, recipe);

    Product product = getProduct(productID);
    if (product==null) {
      //Product newProduct = new Derivative(productID, partnerID, price, currentStock, aggravation, recipe);
      //_products.put(productID, newProduct);
    }

    _batches.put(_batchID++, newBatch);
    
  }

  /*#####################################################*/
  /*----------------------- TRANSACTION ----------------*/
  /*#####################################################*/

  /**
   * @param id transaction id.
   * @return transaction with the corresponding id.
   */
  public Transaction getTransaction(int id) {
    return _transactions.get(id);
  }

  /**
   * Pays a sale given its unique transaction ID.
   * @param id
   */
  public void pay(int id) {
    Transaction transaction = getTransaction(id);
    if (transaction.getPaymentStatus() == false) {
      transaction.setCurrentWarehouseDate(_date);
      transaction.pay();
    }
  }

  /**
   * Registers a warehouse sale.
   * @param partnerID
   * @param deadline
   * @param productID
   * @param productQuantity
   */
  public void registerSale(String partnerID, int deadline, String productID, int productQuantity) {
  }

  /**
   * Registers a warehouse acquisition.
   * @param partnerID
   * @param products
   */
  public void registerAcquisition(String partnerID, Collection<AcquisitionProduct> products) {
  }

  /*#####################################################*/
  /*----------------------- PARTNER ---------------------*/
  /*#####################################################*/

  /**
   * @param id partner id.
   * @return partner with the corresponding id.
   */
  public Partner getPartner(String id) {
    return _partners.get(id);
  }
  
  /**
   * @return Warehouse's partners.
   */
  public Collection<Partner> getPartners() {
    return Collections.unmodifiableCollection(_partners.values());
  }

  /**
   * Register a new partner
   * @param id partner ID.
   * @param name partner name.
   * @param address partner address.
   */
  public void registerPartner(String id, String name, String address) {
    Partner newPartner = new Partner(id, name, address);
    _partners.put(id, newPartner);
  }

  /**
   * Returns a partner's acquisitions as an unmodifiable collection.
   * @param partnerID
   * @return a collection with all partner's notifications.
   */
  public Collection<Acquisition> getPartnerAcquisitions(String partnerID) {
    Partner partner = getPartner(partnerID);
    return partner.getPartnerAcquisitions();
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
    Partner partner = getPartner(partnerID);
    return partner.getPartnerNotifications();
  }

  /**
   * Toggle's partner's notification for a specific product.
   */
  public void toggleNotifications(String partnerID, String productID) {
    Partner partner = getPartner(partnerID);
    Product product = getProduct(productID);
    if (product.getObservers().get(partner) == true) {
      product.removeObserver(partner);
    } else {
      product.registerObserver(partner);
    }
  }
  


}
