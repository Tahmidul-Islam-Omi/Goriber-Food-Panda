package Resources;

import java.io.Serializable;
import java.util.ArrayList;

public class restaurant implements Serializable {
    private String restaurantId;
    private String Name;

    private String Score;

    private String Price;

    private String Zipcode;

    private ArrayList<String> category;

    private ArrayList<food>foods;

    restaurant() {

    }

    public restaurant(String restaurantId, String Name, String Score, String Price, String Zipcode, ArrayList<String> category) {
        this.restaurantId = restaurantId;
        this.Name = Name;
        this.Score = Score;
        this.Price = Price;
        this.Zipcode = Zipcode;
        this.category = category;
        foods= new ArrayList<food>();
    }

    public void addfoods(food fd) {
        foods.add(fd);
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String  getName() {
        return Name;
    }

    public String getScore() {
        return Score;
    }

    public String getPrice() {
        return Price;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public ArrayList<food> getfoods() {
        return foods;
    }

}

