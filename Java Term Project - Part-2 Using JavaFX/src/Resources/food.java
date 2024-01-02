package Resources;

import java.io.Serializable;
import java.util.ArrayList;

public class food implements Serializable {
    private final String foodinRestaurantId;

    private final String foodinRestaurantName;
    private final String foodcategory;
    private final String foodName;
    private final String foodPrice;

    public food(String foodinRestaurantId, String foodinRestaurantName, String foodcategory, String foodName, String foodPrice) {
        this.foodinRestaurantId = foodinRestaurantId;
        this.foodinRestaurantName = foodinRestaurantName;
        this.foodcategory = foodcategory;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public String getFoodinRestaurantId() {
        return foodinRestaurantId;
    }

    public String getFoodinRestaurantName() {
        return foodinRestaurantName;
    }

    public String getFoodcategory() {
        return foodcategory;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

}

