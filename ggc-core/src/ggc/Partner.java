package ggc;

import java.util.*;
import java.io.*;

/**
 * This class is used to represent a partner. A partner has an ID, a name and an address.
 */
public class Partner implements ProductObserver {
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* Partner's ID. */
    private String _id;

    /* Partner's name. */
    private String _name;

    /* Partner's address. */
    private String _address;

    /* Partner's points. */
    private int _points = 0;

    /* Partner's total value of Acquisitions. */
    private double _totalAcquisitionAmount = 0.0;

    /* Partner's total value of Sales. */
    private double _totalSaleAmount = 0.0;

    /* Partner's total value paid. */
    private double _totalSalePaid = 0.0;

    /* Partner's acquisitions. */
    private List<Acquisition> _acquisitions = new ArrayList<Acquisition>();

    /* Partner's sales. */
    private List<Sale> _sales = new ArrayList<Sale>();

    /* Partner's status. */
    private PartnerStatus _status = new NormalPartner(this);
    
    /* Partner's chosen notification delivery */
    private NotificationDelivery _deliveryMode;

    /* Partner's unread notifications.  */
    private List<Notification> _notifications = new ArrayList<Notification>();

    /**
     * Create a partner.
     * @param id partner's id.
     * @param name partner's name.
     * @param address partner's address.
     */
    public Partner(String id, String name, String address) { 
        _id = id;
        _name = name;
        _address = address;
        _deliveryMode = new DefaultNotificationDelivery();
    }

    /**
     * Create a partner.
     * @param id partner's id.
     * @param name partner's name.
     * @param address partner's address.
     * @param deliveryMode partner's notifications delivery mode.
     */
    public Partner(String id, String name, String address, NotificationDelivery mode) { 
        _id = id;
        _name = name;
        _address = address;
        _deliveryMode = mode;
    }

    /**
     * @return the partner's id.
     */
    public String getID() { return _id; }

    /**
     * @return the partner's name.
     */
    public String getName() { return _name; }

    /**
     * @return the partner's address.
     */
    public String getAddress() { return _address; }

    /**
     * @return the partner's points.
     */
    public int getPoints() { return _points; }

    /**
     * @return the partner's total value of Acquisitions.
     */
    public int getTotalAcquisitionAmount() { return (int) Math.round(_totalAcquisitionAmount); }

    /**
     * @return the partner's total value of Sales.
     */
    public int getTotalSaleAmount() { return (int) Math.round(_totalSaleAmount); }

    /**
     * @return the partner's total value paid.
     */
    public int getTotalSalePaid() { return (int) Math.round(_totalSalePaid); }

    /**
     * @return the partner's status.
     */
    public PartnerStatus getStatus() { return _status; }

    /**
     * @return the partner's Sales.
     */
    public Collection<Sale> getPartnerSales() { return Collections.unmodifiableCollection(_sales); }

    /**
     * @return the partner's Acquisitions.
     */
    public Collection<Acquisition> getPartnerAcquisitions() { return Collections.unmodifiableCollection(_acquisitions); }

    /**
     * @return the partner's current delivery mode
     */
    public NotificationDelivery getDeliveryMode() {
        return _deliveryMode;
    }

    /**
     * Returns the partner's pending notifications
     * @return a collection with the partner's pending notifications.
     */
    public Collection<Notification> getPartnerNotifications() {
        return Collections.unmodifiableCollection(_notifications);
    }

    /**
     * Update the partner's points.
     * @param points new partner's points.
     */
    public void updatePoints(int points) {
        _points = points;
    }

    /**
     * Update the partner's status.
     * @param status new partner's status.
     */
    public void updateStatus(PartnerStatus status) {
        _status = status;
    }
    
    /**
     * Adds a sale to partner's sales list.
     * Also updates total sale base prices.
     */
    public void addSale(Sale sale) {
        _sales.add(sale);
    }

    /**
     * Updates partner's total paid sales amount.
     */
    public void addPaidSale(double salePrice) {
        _totalSalePaid += salePrice;
    }

    /**
     * Pays partner's sale.
     */
    public void paySale(Sale sale) {
    }

    /**
     * Add notification.
     */
    @Override
    public void addNotification(String type, String productID, int price) {
        _notifications.add(_deliveryMode.deliverNotification(type, productID, price));
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return getID() + "|" + getName() + "|" + getAddress() + "|" + getStatus().toString() + "|" + getPoints() + "|" + getTotalAcquisitionAmount() + "|" + getTotalSaleAmount() + "|" + getTotalSalePaid(); 
    }

}
