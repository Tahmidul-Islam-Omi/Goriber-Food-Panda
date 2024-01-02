package Restaurant;

import Resources.restaurant;
import Resources.food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FoodListShowController {

    @FXML
    private TableView<food> tableview;
    @FXML
    private TableColumn<food, String> categoryname = new TableColumn<food, String>();
    @FXML
    private TableColumn<food , String> foodname = new TableColumn<food, String>();
    @FXML
    private TableColumn<food , String> price = new TableColumn<food, String>();

    restaurant res;

    RestaurantMain restaurantMain;

    public void init() {

        categoryname.setCellValueFactory( new PropertyValueFactory<>("foodcategory"));
        foodname.setCellValueFactory( new PropertyValueFactory<>("foodName"));
        price.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));

        ArrayList<food> foods = res.getfoods();
        ObservableList<food> foodList = FXCollections.observableArrayList(foods);

        tableview.setItems(foodList);
    }

    public void setMain(RestaurantMain restaurantMain, restaurant res) {
        this.restaurantMain = restaurantMain;
        this.res = res;
    }

}
