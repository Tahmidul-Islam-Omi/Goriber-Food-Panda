package Customer;

import Resources.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Resources.restaurant;
import Resources.food;


import java.io.IOException;
import java.util.ArrayList;

public class HomePageController {

    private CustomerMain customermain;
    ArrayList<restaurant> res;
    ArrayList<food> food;

    SocketWrapper socketWrapper;
    @FXML
    public void SearchRestaurant(ActionEvent actionEvent) throws IOException {
        customermain.loadRestaurantUI(res , food , socketWrapper);
    }
    @FXML
    public void OrderFoodItem(ActionEvent actionEvent) {
        customermain.loadOrderUI(res , food , socketWrapper);
    }
    @FXML
    public void CustomerCart(ActionEvent actionEvent) {
        customermain.loadCustomerCart();
    }

    public void setHomePage(CustomerMain customerMain , ArrayList<restaurant> res , ArrayList<food> food, SocketWrapper socketWrapper) throws IOException {
        this.customermain = customerMain;
        this.res = res;
        this.food = food;
        this.socketWrapper = socketWrapper;
    }

    public void BackButtoninHomePage(ActionEvent event) {
        customermain.showLoginPage();
    }
}
