package Restaurant;

import Resources.Order;
import Resources.SocketWrapper;

import Resources.restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

public class LoginRestaurantController {

    @FXML
    private TextField UserText;
    @FXML
    private TextField PasswordText;
    @FXML
    private Button LoginButton;
    @FXML
    private Button ResetButton;

    private RestaurantMain main;
    private ArrayList<Order> orderlist;

    @FXML
    void LoginAction(ActionEvent event) {
        String userName = UserText.getText();
        String password = PasswordText.getText();

        if (userName.equals("KFC") || userName.equals("IHOP") || userName.equals("Starbucks") || userName.equals("McDonalds") && password.equals("12345")) {
            try {
                connectToServer(userName);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            main.showAlert();
        }
    }

    private void connectToServer(String userName) {

        String serverAddress = "127.0.0.1";
        int serverPort = 33333;

        SocketWrapper socketWrapper = null;

        try {
            socketWrapper = new SocketWrapper(serverAddress, serverPort);
        }

        catch (IOException e) {
            System.out.println(e);
            System.out.println("Failed to connect to server");
        }

        try {
            SocketWrapper.write(userName);
        }

        catch (IOException e) {
            System.out.println(e);
            System.out.println("Failed to write username to server");
        }


        try {

            restaurant res = (restaurant) SocketWrapper.read();
            if(res != null) {
                System.out.println("Received started");
                new ReceiveOrderListThread(socketWrapper , orderlist);
                System.out.println("Received doene");
                main.showHomePage(res , orderlist);
            }

            else {
                System.out.println("Null in loginController");
            }

        }

        catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
            System.out.println("Failed to receive Restaurant Info.");
        }


    }

    @FXML
    void  ResetAction(ActionEvent event) {
        UserText.setText(null);
        PasswordText.setText(null);
    }

    void setLoginRestaurant(RestaurantMain main , ArrayList<Order> orderlist) {
        this.main = main;
        this.orderlist = orderlist;
    }
}
