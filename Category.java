import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    int ID;
    String name;
    HashMap<Product,Integer> products= new HashMap<>();
    public Category(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

}
