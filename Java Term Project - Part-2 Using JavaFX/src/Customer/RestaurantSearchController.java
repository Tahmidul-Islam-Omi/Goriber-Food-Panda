package Customer;

import Resources.SocketWrapper;
import Resources.restaurant;
import Resources.food;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RestaurantSearchController {

    @FXML
    private Label RestaurantID;
    @FXML
    private Label RestaurantName;
    @FXML
    private Label RestaurantScore;
    @FXML
    private Label RestaurantPrice;
    @FXML
    private Label RestaurantZipCode;

    CustomerMain customermain;

    ArrayList<restaurant> restaurantsList;

    ArrayList<food> foodlist;

    SocketWrapper socketWrapper;

    @FXML
    public ChoiceBox<String> SearchByName;
    @FXML
    public ChoiceBox<Double> SearchByScore;
    @FXML
    public ChoiceBox<String> SearchByCategory;
    @FXML
    public ChoiceBox<String> SearchByPrice;
    @FXML
    public ChoiceBox<String> SearchByZipCode;

    void print(restaurant res) {
        RestaurantID.setText(res.getRestaurantId());
        RestaurantName.setText(res.getName());
        RestaurantScore.setText(res.getScore());
        RestaurantPrice.setText(res.getPrice());
        RestaurantZipCode.setText(res.getZipcode());

    }

    static ArrayList<restaurant > restaurantsSearch(ArrayList<restaurant> list, String resName) {
        ArrayList<restaurant> temp = new ArrayList<restaurant>();

        for(int i = 1; i < list.size(); i++) {
            String str1 = list.get(i).getName().toLowerCase();
            String str2 = resName.toLowerCase();

            if( str1.contains(str2) ) {
                temp.add(list.get(i));
            }
        }


        return temp;
    }


    static ArrayList<restaurant > restaurantsScoreSearch(ArrayList<restaurant> list, double a ) {
        ArrayList<restaurant> temp = new ArrayList<restaurant>();

        for(int i = 1; i < list.size(); i++) {

            if( Double.parseDouble (list.get(i).getScore()) == a ) {
                temp.add(list.get(i));
            }

        }

        return temp;
    }

    static ArrayList<restaurant > restaurantsCategorySearch(ArrayList<restaurant> list, String category) {
        ArrayList<restaurant> temp = new ArrayList<restaurant>();

        for(int i = 1; i < list.size(); i++) {

            ArrayList<String> categories = list.get(i).getCategory();

            for(int j = 0 ; j < categories.size(); j++) {
                String str1 = categories.get(j).toLowerCase();
                String str2 = category.toLowerCase();
                if( str1.contains(str2) ) {
                    temp.add(list.get(i));
                }
            }

        }

        return temp;
    }

    static ArrayList<restaurant > restaurantsPriceSearch(ArrayList<restaurant> list, String price) {
        ArrayList<restaurant> temp = new ArrayList<restaurant>();

        for(int i = 1; i < list.size(); i++) {

            if( price.equalsIgnoreCase(list.get(i).getPrice())  ) {
                temp.add(list.get(i));
            }

        }

        return temp;
    }

    static ArrayList<restaurant > restaurantsZipCodeSearch(ArrayList<restaurant> list, String ZipCode) {
        ArrayList<restaurant> temp = new ArrayList<restaurant>();

        for(int i = 1; i < list.size(); i++) {

            if( list.get(i).getZipcode().equals(ZipCode))  {
                temp.add(list.get(i));
            }

        }

        return temp;
    }

    public void setRestaurantSearch(CustomerMain customerMain , ArrayList<restaurant> restaurantsList , ArrayList<food>foodlist, SocketWrapper socketWrapper) {
        this.customermain = customerMain;
        this.restaurantsList = restaurantsList;
        this.foodlist = foodlist;
        this.socketWrapper = socketWrapper;
    }
    public void init() {

        ArrayList<String> ResNameList = new ArrayList<String>();

        for(int i=1; i<restaurantsList.size(); i++) {
            ResNameList.add(restaurantsList.get(i).getName());
        }

        SearchByName.getItems().addAll(ResNameList);

        SearchByName.setOnAction( this::RestaurantNameSearchAction);


        ArrayList<Double> ResScoreList = new ArrayList<Double>();

        for(int i=1; i<restaurantsList.size(); i++) {
            ResScoreList.add( Double.parseDouble(restaurantsList.get(i).getScore()));
        }

        SearchByScore.getItems().addAll(ResScoreList);
        SearchByScore.setOnAction( this::SearchByScoreAction);

        ArrayList<String>ResPriceList = new ArrayList<>();
        ResPriceList.add("$");
        ResPriceList.add("$$");
        ResPriceList.add("$$$");

        SearchByPrice.getItems().addAll(ResPriceList);
        SearchByPrice.setOnAction( this::SearchByPriceAction);

        ArrayList<String> ResCategoryList = new ArrayList<>();

        for(int i=1; i<restaurantsList.size(); i++) {
            ArrayList<String> temp = restaurantsList.get(i).getCategory();

            ResCategoryList.addAll(temp);
        }

        SearchByCategory.getItems().addAll(ResCategoryList);
        SearchByCategory.setOnAction(this:: searchByCategoryAction);

        ArrayList<String> ResZipCodeList = new ArrayList<>();

        for(int i=1; i<restaurantsList.size(); i++) {
            ResZipCodeList.add(restaurantsList.get(i).getZipcode());
        }

        SearchByZipCode.getItems().addAll(ResZipCodeList);
        SearchByZipCode.setOnAction(this:: searchByZipCodeAction);


    }

    private void searchByZipCodeAction(ActionEvent event) {
        String zipcode = SearchByZipCode.getValue();
        ArrayList<restaurant> temp = restaurantsZipCodeSearch(restaurantsList ,zipcode);
        print(temp.get(0));
    }


    private void searchByCategoryAction(ActionEvent event) {
        String category= SearchByCategory.getValue();
        ArrayList<restaurant> temp = restaurantsCategorySearch(restaurantsList , category);
        print(temp.get(0));
    }

    private void SearchByPriceAction(ActionEvent event) {
        String price = SearchByPrice.getValue();
        ArrayList<restaurant> temp = restaurantsPriceSearch(restaurantsList , price);
        print(temp.get(0));
    }

    private void RestaurantNameSearchAction(ActionEvent event) {

        String resName = SearchByName.getValue();

        if(resName == null) {
            System.out.println("NULL");
        }

        ArrayList<restaurant> temp = restaurantsSearch(restaurantsList , resName);

        if(temp.size() == 0) {
            System.out.println("TempNULL");
        }
        print(temp.get(0));
    }

    private void SearchByScoreAction(ActionEvent event) {
        double score =SearchByScore.getValue();
        ArrayList<restaurant> temp = restaurantsScoreSearch(restaurantsList , score);
        print(temp.get(0));
    }

    @FXML
    public void BackButtoninRestaurantSearching(ActionEvent event) throws IOException {
        customermain.showHomePage(restaurantsList , foodlist , socketWrapper );
    }
}
