import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Customer{
    String name;
    int age;
    String phonenumber;
    String email_ID;
    String password;
    String status;
    double wallet;
    HashMap<Product,Integer> cart=new HashMap<Product,Integer>();
    HashMap<Giweaway, Integer> giveawaycart= new HashMap<>();
    ArrayList<Integer> Coupons= new ArrayList<>();

    public Customer(String name, int age, String phonenumber, String email_ID, String password) {
        this.name = name;
        this.age = age;
        this.phonenumber = phonenumber;
        this.email_ID = email_ID;
        this.password = password;
        this.status="Normal";
        this.wallet=1000;
    }
    public void upgradeStatus(String which) {

        if(which.equals("Elite")){
            this.status="Elite";
            this.makePayment(300);

            System.out.println("Membership upgraded to Elite");
        }
        else if(which.equals("Prime")){
           this.status="Prime";
           this.makePayment(200);

            System.out.println("Membership upgraded to Prime");
        }
        else{
            System.out.println("Invalid choice");
        }





        System.out.println("Membership upgraded");
    }
    public void addMoney(double amount) {

        this.wallet+=amount;
    }
    public void addToCart(Product product, int quantity) {
        if (this.cart.containsKey(product)) {
            this.cart.replace(product, this.cart.get(product) + quantity);
        } else {
            this.cart.put(product, quantity);
        }

        System.out.println("Added product to cart");
    }

    public void addToGiweawayCart(Giweaway giweaway, int quantity) {
        giveawaycart.put(giweaway,quantity);

        System.out.println("Added giveaway to cart");
    }
    public void makePayment(double cost){

        this.wallet-=cost;
    }
    public void EmptyCart(){
        this.cart.clear();


        System.out.println("Cleared cart");
    }
    public void removeFromGiweawayCart(Giweaway giweaway) {
        for (Giweaway g : giveawaycart.keySet()) {
            if (g.equals(giweaway)) {
                giveawaycart.remove(g);
                System.out.println("Removed from cart");
                break;
            }
        }
    }
    public void viewCart() {
        for(Product p:cart.keySet()){
            System.out.println(p.name+" "+cart.get(p));
        }

        System.out.println("Viewed cart of individual products");
        for(Giweaway g:giveawaycart.keySet()){
            System.out.println(g.p1.name+" "+g.p2.name+" "+giveawaycart.get(g));
        }
    }

    public void viewWallet() {
        System.out.println(this.wallet);
    }

    public void deliverydays(){
        System.out.println("Delivery in 7-10 days");
    }
    public double deliverycharges(double totalprice){
        System.out.println(100+0.05*totalprice);
        return 100+0.05*totalprice;


    }

    public void browsedeals(HashMap<Giweaway, ArrayList<Integer>> allgiweaways){
        for(Giweaway giweaway: allgiweaways.keySet()) {
            System.out.println(giweaway.p1.name);
            System.out.println(giweaway.p2.name);
            System.out.println("Normal price: " + allgiweaways.get(giweaway).get(0));
        }
        System.out.println("Browsed deals");
    }

    public double admin_set_discount(double proID, HashMap<Integer, ArrayList<Integer>> alldiscounts,ArrayList<Category> categories) {
        if (alldiscounts.containsKey(proID)) {
            //System.out.println("Admin set discount: " + (double) (alldiscounts.get(proID).get(0)) / 100);


        }
        for (Category category : categories) {
            for (Product product : category.products.keySet()) {
                if (product.proID == proID) {
                    if(alldiscounts.get(proID)!=null){return  product.price * (double) (alldiscounts.get(proID).get(0)) / 100;}
                }
            }

        }
        return 0 ;
    }

    public void checkout(HashMap<Integer, ArrayList<Integer>> alldiscounts,ArrayList<Category> categories, HashMap <Giweaway, ArrayList<Integer>> allgiweaways){
        ArrayList<Double> cartprice= new ArrayList<Double>();
        System.out.println("Checked out");
        double undiscountedprice=0;
        for(Product product: this.cart.keySet()) {
            System.out.println(product.name);
            System.out.println(this.cart.get(product));
            System.out.println(product.price);
            undiscountedprice+=product.price*this.cart.get(product);

            double discount=this.admin_set_discount(product.proID,alldiscounts,categories);
            System.out.println("Discount on this product is" +discount);
            double itemprice=product.price-discount;
            System.out.println("Discounted price of item"+ itemprice);
            cartprice.add(itemprice*this.cart.get(product));
        }
        for(Giweaway g: this.giveawaycart.keySet()){
            System.out.println("Price is"+ allgiweaways.get(g).get(0));
            cartprice.add(Double.valueOf(allgiweaways.get(g).get(0)));
            undiscountedprice+=Double.valueOf(allgiweaways.get(g).get(0));

        }
        double totalprice=0;
        for(int i=0;i<cartprice.size();i++){
            totalprice+=cartprice.get(i);
        }

        double deliverycharges=this.deliverycharges(undiscountedprice);
        totalprice+=deliverycharges;
        System.out.println("Total price of cart"+ totalprice);

        this.deliverydays();
        if(this.wallet>=totalprice){
            this.makePayment(totalprice);
            for(Category category:categories){
                for(Product product:category.products.keySet()){
                    if(this.cart.containsKey(product)){
                        category.products.replace(product,category.products.get(product)-this.cart.get(product));
                    }
                }
            }

            this.EmptyCart();
            System.out.println("Payment successful");

        }
        else{
            System.out.println("Insufficient money");
        }
    }





}


