package Restaurant;

import Resources.Order;
import Resources.SocketWrapper;
import Resources.restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.exit;
import static javafx.application.Application.launch;

public class RestaurantMain extends Application {
    private Stage stage;

    ArrayList<Order> orderlist = new ArrayList<Order>();

    @Override
    public void start(Stage primarystage)  {
        stage = primarystage;
        try {
            showLoginPage();
        }

        catch (Exception e) {
            System.out.println("Failed");
        }
    }

    private void showLoginPage() {
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginRestaurant.fxml"));

        Parent root = null;

        try {
            root = loader.load();
        }

        catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to load the FXML file.");

        }

        LoginRestaurantController controller = loader.getController();

        controller.setLoginRestaurant(this , orderlist);

        stage.setScene(new Scene(root,800, 600));
        stage.show();

    }

    public void showHomePage( restaurant res , ArrayList<Order> orderlist) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RestaurantsHomePage.fxml"));


        Parent root = null;

        try {
            root = loader.load();
        }

        catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to load the FXML file.");

        }

        // Loading the controller
        RestaurantsHomePageController controller = loader.getController();
        controller.setMain(this , res , orderlist);

        // Set the primary stage
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showRestaurantsInformation(restaurant res, ArrayList<Order> orderlist) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RestaurantInfoShowing.fxml"));


        Parent root = null;

        try {
            root = loader.load();
        }

        catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to load the FXML file.");

        }

        // Loading the controller
        RestaurantInfoShowingController controller = loader.getController();
        controller.setMain(this , res , orderlist);
        controller.show();

        // Set the primary stage
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

    }

    public void showFoodList(restaurant res , ArrayList<Order> orderlist) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("FoodListShowing.fxml"));
            System.out.println("Hi");
            //exit(0);
        }
        catch (Exception e) {
            System.out.println("Failed to open the FXML file.");
        }

        Parent root ;
        root = loader.load();


        // Loading the controller
        FoodListShowController controller = loader.getController();
        controller.setMain(this , res);
        controller.init();

        // Set the primary stage
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

    }

    public void showOrderList(ArrayList<Order> orderlist) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OrderList.fxml"));


        Parent root = null;

        try {
            root = loader.load();
        }

        catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to load the Orderlist FXML file.");

        }

        // Loading the controller
        OrderListController controller = loader.getController();
        controller.setOrderlist(this , orderlist);
        controller.printOrderlist();

        // Set the primary stage
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

    }
}
