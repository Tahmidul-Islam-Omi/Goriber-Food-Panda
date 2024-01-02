package Customer;

import Resources.SocketWrapper;
import Resources.restaurant;
import Resources.food;
import Restaurant.RestaurantMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class LogInPageController {

    @FXML
    private TextField UserText;
    @FXML
    private TextField PasswordText;
    @FXML
    private Button LoginButton;
    @FXML
    private Button ResetButton;

    private CustomerMain customerMain;

    @FXML
    public void LoginAction(ActionEvent actionEvent) {
        String userName = UserText.getText();
        String password = PasswordText.getText();

        if (password.equals("1")) {
            try {
                connectToServer(userName);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            customerMain.showAlert();
        }

    }

    private void connectToServer(String userName) throws IOException {

        String serverAddress = "127.0.0.1";
        int serverPort = 33333;

        SocketWrapper socketWrapper = null;

        try {
            socketWrapper = new SocketWrapper(serverAddress, serverPort);
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Failed to connect to server");
        }

        try {
            SocketWrapper.write(userName);
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Failed to write username to server");
        }

        int flag1 = 0;
        int flag2 = 0;

        ArrayList<restaurant> restaurantsList = null;
        ArrayList<food> foodList = null;

        while (true) {

            Object receivedObject = null;

            try {

                receivedObject = SocketWrapper.read();

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Failed to read object from socket");
                System.out.println(e);
            }

            ArrayList<?> receivedList = (ArrayList<?>) receivedObject;

            if (receivedList != null) {

                if (!receivedList.isEmpty() && receivedList.get(0) instanceof restaurant) {
                    restaurantsList = (ArrayList<restaurant>) receivedList;
                }

                if (!receivedList.isEmpty() && receivedList.get(0) instanceof food) {
                    foodList = (ArrayList<food>) receivedList;
                }

            }

            if (restaurantsList != null && foodList != null) {
                break;
            }

        }

        customerMain.showHomePage(restaurantsList, foodList , socketWrapper);

    }

    public void ResetAction (ActionEvent actionEvent){

        UserText.setText(null);
        PasswordText.setText(null);

    }

    void setLoginPage (CustomerMain customerMain){
        this.customerMain = customerMain;
    }

}

