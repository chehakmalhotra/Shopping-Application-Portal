import java.util.ArrayList;
import java.util.HashMap;

public class Admin {
    String username;
    String roll_num;





    public Admin(String username, String roll_num) {
        this.username = username;
        this.roll_num = roll_num;
    }

    public void addCategory(ArrayList<Category> categories, int ID, String name) {

        categories.add(new Category(ID, name)) ;
        System.out.println("Category added");
    }

    public void deleteCategory(ArrayList<Category> categories, int ID, String name) {
        for(int i=0;i<categories.size();i++){
            if(categories.get(i).ID==ID && categories.get(i).name.equals(name)){
                categories.remove(i);
                System.out.println("Category deleted");
                return;
            }
        }
        System.out.println("Category deleted");
    }

    public void addProduct(ArrayList<Category> categories,int catID, String name, double price, String details, double proid, int quantity) {
        Product product= new Product(name, price, details, proid);
        for(Category category: categories){
            if(category.ID==catID){
                category.products.put(product,quantity);
                System.out.println("Product added");
                return;
            }
        }


    }
    public void deleteProduct(ArrayList <Category> categories,int catID,double prID) {
        for (Category category : categories) {
            if (category.ID == catID) {
                for (int i = 0; i < category.products.size(); i++) {

                    Product prod= (Product) category.products.keySet().toArray()[i];
                    if (prod.proID== prID) {
                        category.products.remove(i);
                        System.out.println("Product deleted");
                        return;
                    }


                }
            }
        }
    }


    public void setDiscount(HashMap alldiscounts,double proID, int p1, int p2, int p3){
        ArrayList<Integer> discounts = new ArrayList<Integer>();
        discounts.add(p1);
        discounts.add(p2);
        discounts.add(p3);
        alldiscounts.put(proID, discounts);
        System.out.println("Discount set");

    }
    public void giveaway(HashMap allgiweaways, Giweaway giveaway,double normal, double elite, double prime){
        ArrayList<Double> giweaways = new ArrayList<>();
        giweaways.add(normal);
        giweaways.add(elite);
        giweaways.add(prime);
        allgiweaways.put(giveaway, giweaways);
        System.out.println("Giweaway set");



    }



}
