package Restaurant;
import Resources.Order;
import Resources.SocketWrapper;
import Resources.restaurant;
import Resources.food;

import java.io.IOException;
import java.util.ArrayList;

public class ReceiveOrderListThread implements Runnable {

    private Thread thr;
    private SocketWrapper socketWrapper;

    private ArrayList<Order> orderList;

    ReceiveOrderListThread(SocketWrapper socketWrapper , ArrayList<Order> orderList)  {
        System.out.println("Restaurant thread started");
        this.socketWrapper = socketWrapper;
        this.orderList = orderList;
        this.thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {

        while (true) {

            Object o = null;

            try {
                o = SocketWrapper.read();
                System.out.println("Restaurant Read Done");
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (o instanceof Order) {
                Order obj = (Order) o;
                System.out.println("Added in orderlist");
                orderList.add(obj);
            }
        }

    }

}
