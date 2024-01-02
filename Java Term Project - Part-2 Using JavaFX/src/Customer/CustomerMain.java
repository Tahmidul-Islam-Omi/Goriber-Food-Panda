package Customer;

import Resources.SocketWrapper;
import Resources.food;
import Resources.restaurant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerMain extends Application {

    private Stage stage;

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

    public void showLoginPage() {

        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginPage.fxml"));

        Parent root = null;

        try {
            root = loader.load();
        }

        catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to load the FXML file.");

        }

        LogInPageController controller = loader.getController();

        controller.setLoginPage(this);

        stage.setScene(new Scene(root,700 , 500));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public void showHomePage(ArrayList<restaurant> res, ArrayList<food> fd , SocketWrapper socketWrapper) throws IOException {

        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("HomePage.fxml"));

        Parent root = null;

        try {
            root = loader.load();
        }

        catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to load the HomePage FXML file.");

        }

        HomePageController controller = loader.getController();

        controller.setHomePage(this , res, fd , socketWrapper);

        stage.setScene(new Scene(root,700 , 500));
        stage.show();

    }

    public void loadRestaurantUI(ArrayList<restaurant> res, ArrayList<food> food, SocketWrapper socketWrapper) throws IOException {

        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("RestaurantSearch.fxml"));

        Parent root = null;

//        try {
//            root = loader.load();
//        }
//
//        catch (Exception e) {
//            System.out.println(e);
//            System.out.println("Failed to load the RestaurantSearch FXML file.");
//
//        }

        root = loader.load();

        RestaurantSearchController controller = loader.getController();

        controller.setRestaurantSearch(this , res, food , socketWrapper);
        controller.init();

        stage.setScene(new Scene(root,700 , 500));
        stage.show();

    }

    public void loadOrderUI(ArrayList<restaurant> res , ArrayList<food> fd, SocketWrapper socketWrapper) {

        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("OrderSearchUI.fxml"));

        Parent root = null;

        try {
            root = loader.load();
        }

        catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to load the OrderSearchUI FXML file.");

        }

        OrderSearchUIController controller = loader.getController();

        controller.setOrderSearch(this , res, fd , socketWrapper);
        controller.init();

        stage.setScene(new Scene(root,1000 , 800));
        stage.show();

    }

    public void loadCustomerCart() {

    }
}
