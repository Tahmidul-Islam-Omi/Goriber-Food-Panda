package Restaurant;

import Resources.Order;
import Resources.restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class RestaurantInfoShowingController {

    @FXML
    public TextField RestaurantID;
    @FXML
    public TextField RestaurantName;
    @FXML
    public TextField RestaurantScore;
    @FXML
    public TextField RestaurantPrice;
    @FXML
    public TextField RestaurantZipcode;

    restaurant res;

    ArrayList<Order> orderList;

    RestaurantMain restaurantMain;


    public void setMain(RestaurantMain restaurantMain, restaurant res, ArrayList<Order> orderList) {
        this.restaurantMain = restaurantMain;
        this.res = res;
        this.orderList = orderList;
    }

    void show() {
        RestaurantID.setText(res.getRestaurantId());
        RestaurantName.setText(res.getName());
        RestaurantScore.setText(res.getScore());
        RestaurantPrice.setText(res.getPrice());
        RestaurantZipcode.setText(res.getZipcode());
    }

    public void BackButtoninRestaurantInfoShowing(ActionEvent event) {
        restaurantMain.showHomePage(res , orderList);
    }
}
