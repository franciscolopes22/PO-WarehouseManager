package ggc;

public class DefaultNotificationDelivery implements NotificationDelivery {
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;
    
    /**
     * /* Adds a notification to the pending notifications.
     */
    @Override
    public Notification deliverNotification(String event, String pID, int price) {
        return new Notification("", event, pID, price);
    }
}
