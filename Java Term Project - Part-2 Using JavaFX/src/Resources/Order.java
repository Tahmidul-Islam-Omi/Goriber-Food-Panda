package Resources;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    private String CustomerName;
    private String toRestaurantName;
    private food foods;

    private Integer foodQuantity;

    public Order() {
    };

    public void settoRestaurantName(String toRestaurantName) {
        this.toRestaurantName = toRestaurantName;
    }

    public String gettoRestaurantName() {
        return toRestaurantName;
    }

    public void setFoodInfo(food foods) {
        this.foods = foods;
    }

    public food getFoodInfo() {
        return foods;
    }

    public void setCustomerName(String CustomerName) { this.CustomerName =CustomerName;}
    String getCustomerName() {
        return CustomerName;
    }

    public Integer getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(Integer foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

}
