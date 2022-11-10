import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EliteCustomer extends Customer implements Discount{

    public EliteCustomer(String name, int age, String phonenumber, String email_ID, String password) {
        super(name, age, phonenumber, email_ID, password);
        this.status="Elite";
    }

    @Override
    public void deliverydays(){
        System.out.println("Delivery in 2 days");

    }
    @Override
    public double deliverycharges(double totalprice){

        System.out.println("Delivery charges are 100");
        return 100;
    }

    public void browsedeals(HashMap<Giweaway, ArrayList<Integer>> allgiweaways){
        for(Giweaway giweaway: allgiweaways.keySet()) {
            System.out.println(giweaway.p1.name);
            System.out.println(giweaway.p2.name);
            System.out.println("Elite price: " + allgiweaways.get(giweaway).get(2));
        }
        System.out.println("Browsed deals");
    }

    public double member_specific_discount(double product_price){
       //System.out.println("Member specific discount: "+ 0.1*product_price);
        return 0.1*product_price;
    }

    public void getfreesurprise(){
        Random rd = new Random();

        System.out.println("Free surprise"+rd.nextBoolean());
    }

    public void get_coupon(double totalprice){
        if (totalprice>5000){

            int randomNum2 = ThreadLocalRandom.current().nextInt(3, 4 + 1);
            for (int i = 0; i < randomNum2; i++) {
                this.Coupons.add(ThreadLocalRandom.current().nextInt(5, 15 + 1));
            }
            System.out.println("Coupons: " + this.Coupons);

        }
    }

    public double use_coupon(double product_price){
        if (this.Coupons.size()>0){
            Collections.sort(this.Coupons, Collections.reverseOrder());
            //System.out.println("Coupon used");
            //this.Coupons.remove(0);
            return ((double)this.Coupons.get(0))*product_price/(double)100;

        }
        return 0;
    }

    public double admin_set_discount(double proID, HashMap<Integer, ArrayList<Integer>> alldiscounts,ArrayList<Category> categories) {
        if (alldiscounts.containsKey(proID)) {
            //System.out.println("Admin set discount: " +(double) (((double) (alldiscounts.get(proID).get(1))) / 100));


        }
        for (Category category : categories) {
            for (Product product : category.products.keySet()) {
                if (product.proID == proID) {
                    if(alldiscounts.get(proID)!=null) return (double)(product.price * ((double) (alldiscounts.get(proID).get(2))) / 100);
                }
            }

        }
        return 0 ;
    }
    @Override
    public void checkout(HashMap<Integer, ArrayList<Integer>> alldiscounts,ArrayList<Category> categories, HashMap <Giweaway, ArrayList<Integer>> allgiweaways){
        ArrayList<Double> cartprice= new ArrayList<Double>();
        System.out.println("Checked out");
        double undiscountedprice=0;
        for(Product product: this.cart.keySet()) {
            System.out.println(product.name);
            System.out.println(this.cart.get(product));
            System.out.println(product.price);
            undiscountedprice+=product.price;
            System.out.println("Admin set disocunt is"+this.admin_set_discount(product.proID, alldiscounts, categories));
            System.out.println("Member specific discount is"+this.member_specific_discount(product.price));
            System.out.println("Coupon disocunt is"+this.use_coupon(product.price));
            double discount=Math.max(this.admin_set_discount(product.proID,alldiscounts,categories),Math.max(this.member_specific_discount(product.price),this.use_coupon(product.price)));
            System.out.println("Discount on this product is" +discount);
            double itemprice=product.price-discount;
            System.out.println("Discounted price of item"+itemprice);
            cartprice.add(itemprice*this.cart.get(product));
        }
        for(Giweaway g: this.giveawaycart.keySet()){
            System.out.println("Price is"+ allgiweaways.get(g).get(2));
            cartprice.add(Double.valueOf(allgiweaways.get(g).get(2)));
            undiscountedprice+=Double.valueOf(allgiweaways.get(g).get(2));

        }
        double totalprice=0;
        for(int i=0;i<cartprice.size();i++){
            totalprice+=cartprice.get(i);
        }

        double deliverycharges=this.deliverycharges(undiscountedprice);
        totalprice+=deliverycharges;
        System.out.println("Total price"+ totalprice);
        //this.deliverycharges(totalprice);
        this.deliverydays();
        this.getfreesurprise();
        this.get_coupon(totalprice);
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











