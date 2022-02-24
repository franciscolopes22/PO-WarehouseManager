package ggc;

import java.io.*;

public interface NotificationDelivery extends Serializable {
    /**
     * Add notification to pending notifications.
     */
    Notification deliverNotification(String type, String pID, int price);

}
