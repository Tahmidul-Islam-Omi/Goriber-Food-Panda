package Restaurant;

import Resources.Order;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import java.util.ArrayList;

public class OrderListController {
    @FXML
    private TableColumn foodname;
    @FXML
    private TableColumn foodprice;
    @FXML
    private TableColumn foodquantity;
    @FXML
    private Label label;
    ArrayList<Order> orderlist;

    RestaurantMain restaurantMain;
    void setOrderlist( RestaurantMain restaurantMain , ArrayList<Order> orderlist) {
        this.restaurantMain = restaurantMain;
        this.orderlist = orderlist;
    }

    void printOrderlist() {
        System.out.println(orderlist.size());
        for (int i = 0; i < orderlist.size(); i++) {
            System.out.println(orderlist.get(i).getFoodInfo().getFoodName() + " " + orderlist.get(i).getFoodQuantity());
        }
    }
}
