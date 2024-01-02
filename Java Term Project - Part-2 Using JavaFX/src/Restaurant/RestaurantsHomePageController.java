package Restaurant;

import Resources.Order;
import Resources.SocketWrapper;

import Resources.restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;

public class RestaurantsHomePageController {

    @FXML
    private Button RestaurantInfoButton;
    @FXML
    private Button FoodListButton;
    @FXML
    private Button OrderListButton;
    RestaurantMain restaurantMain;
    restaurant res;

    ArrayList<Order> orderList ;

    public void setMain(RestaurantMain restaurantMain, restaurant res , ArrayList<Order> orderList) {
        this.restaurantMain = restaurantMain;
        this.res = res;
        this.orderList = orderList;
    }

    public void RestaurantInfoButton(ActionEvent actionEvent) {
        restaurantMain.showRestaurantsInformation(res, orderList);
    }
    public void FoodListButton(ActionEvent actionEvent) throws IOException {
        if (res != null) {
            restaurantMain.showFoodList(res , orderList);
        }

    }
    public void OrderListButton(ActionEvent actionEvent) {
        restaurantMain.showOrderList(orderList);
    }
}
