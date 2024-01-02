package Admin;

import Resources.Order;
import Resources.SocketWrapper;

import java.io.IOException;
import java.util.HashMap;

public class AdminReadThread implements Runnable {
    private Thread t;

    HashMap<String, SocketWrapper> clientMap;

    private SocketWrapper socketWrapper;

    AdminReadThread(SocketWrapper socketWrapper , HashMap<String, SocketWrapper> clientMap) {
        System.out.println("Thread Started");
        this.clientMap = clientMap;
        this.socketWrapper = socketWrapper;
        this.t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while (true) {
            Object o = null;
            try {
                o = SocketWrapper.read();
                System.out.println("Read Done");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Failed to read");
                throw new RuntimeException(e);
            }

            if (o instanceof Order) {
                Order obj = (Order) o;
                String to = obj.gettoRestaurantName();

                SocketWrapper nu = clientMap.get(to);

                if (nu != null) {
                    try {
                        nu.write(obj);
                        System.out.println("Write Done");
                    } catch (IOException e) {
                        System.out.println("Failed to forward");
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }
}
