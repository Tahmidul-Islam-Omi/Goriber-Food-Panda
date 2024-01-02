package Customer;

import Resources.Order;
import Resources.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import Resources.restaurant;
import Resources.food;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.exit;

public class OrderSearchUIController {
    public ChoiceBox<String> foodrestaurantddb;
    public ChoiceBox<Integer> quantityddb;
    public ChoiceBox<String> restaurantdropdownbox;
    
    Order order = new Order();
    
    int foodlistshow = 0;

    ArrayList<restaurant> resInfoList;
    ArrayList<food> foods;

    SocketWrapper socketWrapper;

    CustomerMain customermain;

    void setOrderSearch(CustomerMain customerMain , ArrayList<restaurant>resInfoList , ArrayList<food> foods, SocketWrapper socket) {
        this.customermain = customerMain;
        this.resInfoList = resInfoList;
        this.foods = foods;
        this.socketWrapper = socketWrapper;
    }
    public void init() {

        ArrayList<String> reslist = new ArrayList<>();

        for(int i=1; i<resInfoList.size(); i++) {
            reslist.add(resInfoList.get(i).getName());
        }

        restaurantdropdownbox.getItems().addAll(reslist);
        restaurantdropdownbox.setOnAction(this::restaurantdropdownboxAction);

    }

    private void restaurantdropdownboxAction(ActionEvent event) {
        String resname = restaurantdropdownbox.getValue();
        
        order.settoRestaurantName(resname);

        restaurant res = null;

        for(int  i=1; i< resInfoList.size(); i++) {
            if(resInfoList.get(i).getName().equals(resname)) {
                res = resInfoList.get(i);
                break;
            }
        }

        if(res == null) {
            System.out.println("Restaurant Doesn't exist!");
            exit(0);
        }

        ArrayList<String> foodname = new ArrayList<>();
        
        for(int i=0; i<res.getfoods().size(); i++) {
            foodname.add(res.getfoods().get(i).getFoodName());
        }

        if(foodname == null) {
            System.out.println("No food");
            exit(0);
        }

        foodrestaurantddb.getItems().addAll(foodname);
        foodrestaurantddb.setOnAction(this::foodlistdropdownboxAction);

    }

    private void foodlistdropdownboxAction(ActionEvent event) {
        String foodname = foodrestaurantddb.getValue();
        
        food fd = null;
        
        for(int i=0; i< foods.size(); i++) {
            if(foods.get(i).getFoodName().equals(foodname)) {
                fd = foods.get(i);
                break;
            }
        }

        order.setFoodInfo(fd);

        ArrayList<Integer> num = new ArrayList<Integer>();

        for(int i=1; i<=10; i++) {
            num.add(i);
        }

        quantityddb.getItems().addAll(num);

        quantityddb.setOnAction(this::quantityddbAction);


    }

    private void quantityddbAction(ActionEvent event)  {

        try {
            int quantiy = quantityddb.getValue();
            order.setFoodQuantity(quantiy);
            ordersent(order);

        }

        catch (IOException e) {

            System.out.println("Failed to send order.");
            System.out.println(e);

        }

    }

    private void ordersent(Order order) throws IOException {
        System.out.println("Quantity:- " + order.getFoodQuantity());
        System.out.println("Res Name: " + order.gettoRestaurantName());
        System.out.println("Food Name:- " + order.getFoodInfo().getFoodName());
        SocketWrapper.write(order);
    }
}
