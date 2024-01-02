package Admin;

import Resources.Order;
import Resources.SocketWrapper;
import Resources.restaurant;
import Resources.food;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServingClient {
    ArrayList<restaurant> restaurantsInfo;
    ArrayList<food> menuInfo;
    ServingClient(ArrayList<restaurant> restaurantsInfo , ArrayList<food> menuInfo ) {
        this.restaurantsInfo = restaurantsInfo;
        this.menuInfo = menuInfo;
    }

    public void waiting() {

        ServerSocket serverSocket;
        HashMap<String, SocketWrapper> clientMap;

        clientMap = new HashMap<>();

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                System.out.println("Server is waiting for Client...");
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket , clientMap);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }

    }

    public void serve(Socket clientSocket, HashMap<String, SocketWrapper> clientMap) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        String ClientName = (String) SocketWrapper.read();

        System.out.println(ClientName + " is Connected.");

        clientMap.put(ClientName, socketWrapper);

        restaurant res = null;

        for(int i=0; i<restaurantsInfo.size(); i++) {
            if( restaurantsInfo.get(i).getName().equals(ClientName)) {
                res = restaurantsInfo.get(i);
                SocketWrapper.write(res);
            }
        }

        if( res == null) {

            SocketWrapper.write(restaurantsInfo);
            SocketWrapper.write(menuInfo);

            new AdminReadThread( socketWrapper,clientMap);

        }
    }

}
