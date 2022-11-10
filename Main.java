

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
       // ArrayList<PrimeCustomer> primeCustomers = new ArrayList<PrimeCustomer>();
        // ArrayList<EliteCustomer> eliteCustomers = new ArrayList<EliteCustomer>();
        HashMap<Giweaway,ArrayList<Integer>> allgiweaways = new HashMap<>();
        HashMap<Integer,ArrayList<Integer>> alldiscounts = new HashMap<>();
        ArrayList<Category> categories= new ArrayList<>();
        Admin admini = new Admin("admin", "admin");
        admins.add(admini);
        Category category1 = new Category(1, "Electronics");
        categories.add(category1);
        category1.products.put(new Product("Laptop", 50000, "Dell", 1),1);
        //category1.products.put(new Product("Mobile", 10000, "Samsung", 2),1);


        Customer customer1 = new Customer("Rahul", 20, "1234567890", "jj", "yy");
        customers.add(customer1);

        

        while (true) {
            System.out.println("1 Enter as Admin \n 2) Explore the Product Catalog \n 3) Show Available Deals \n 4) Enter as Customer \n 5) Exit the Application");
            Scanner sc = new Scanner(System.in);
            int choice1 = sc.nextInt();
            if (choice1 == 1) {
                System.out.println("Enter username");
                String username = sc.next();
                System.out.println("Enter password");
                String roll_num = sc.next();
                for(Admin admin: admins){
                    if(admin.username.equals(username) && admin.roll_num.equals(roll_num)){
                        while(true) {
                            System.out.println("1) Add Category \n 2) Delete Category \n 3) Add Product \n 4) Delete Product \n 5) Set Discount \n 6) Giveaway 7) Exit");
                            int choice2 = sc.nextInt();
                            if (choice2 == 1) {

                                System.out.println("Enter Category ID");
                                int ID = sc.nextInt();
                                while(true){
                                    boolean flag = false;
                                    for(Category category: categories){
                                        if(category.ID==ID){
                                            flag = true;
                                            break;
                                        }
                                    }
                                    if(flag){
                                        System.out.println("Category ID already exists");
                                        ID = sc.nextInt();
                                    }
                                    else{
                                        break;
                                    }
                                }


                                System.out.println("Enter Category name");
                                String name = sc.next();
                                admin.addCategory(categories, ID, name);
                                System.out.println("Enter product name");
                                String product_name = sc.next();
                                System.out.println("Enter product price");
                                double product_price = sc.nextDouble();
                                System.out.println("Enter product details");
                                String product_details = sc.next();
                                System.out.println("Enter product ID");
                                double product_ID = sc.nextDouble();
                                System.out.println("Enter product quantity");
                                int product_quantity = sc.nextInt();
                                admin.addProduct(categories, ID, product_name, product_price, product_details, product_ID, product_quantity);
                            } else if (choice2 == 2) {
                                System.out.println("Enter ID");
                                int ID = sc.nextInt();
                                System.out.println("Enter name");
                                String name = sc.next();
                                admin.deleteCategory(categories, ID, name);
                            } else if (choice2 == 3) {
                                System.out.println("Enter category ID");
                                int catID = sc.nextInt();
                                System.out.println("Enter product ID");
                                double proID = sc.nextDouble();
                                System.out.println("Enter product name");
                                sc.nextLine();
                                String name = sc.next();
                                System.out.println("Enter product price");
                                sc.nextLine();
                                double price = sc.nextDouble();
                                System.out.println("Enter product details");
                                String details = sc.next();
                                System.out.println("Enter product quantity");
                                int quantity = sc.nextInt();
                                admin.addProduct(categories, catID, name, price, details, proID, quantity);
                            } else if (choice2 == 4) {
                                System.out.println("Enter category ID");
                                int catID = sc.nextInt();
                                System.out.println("Enter product ID");
                                int proID = sc.nextInt();

                                admin.deleteProduct(categories, catID, proID);
                            } else if (choice2 == 5) {
                                System.out.println("Enter product ID");
                                double prodID = sc.nextDouble();

                                System.out.println("Enter discount for normal customers");
                                int discount1 = sc.nextInt();
                                System.out.println("Enter discount for prime customers");
                                int discount2 = sc.nextInt();
                                System.out.println("Enter discount for elite customers");
                                int discount3 = sc.nextInt();
                                admin.setDiscount(alldiscounts, prodID, discount1, discount2, discount3);
                            } else if (choice2 == 6) {

                                System.out.println("Enter product 1 ID");
                                double p1ID = sc.nextDouble();
                                System.out.println("Enter product 2 ID");
                                double p2ID = sc.nextDouble();

                                System.out.println("Enter normal price");
                                double normal_price = sc.nextDouble();
                                System.out.println("Enter prime price");
                                double prime_price = sc.nextDouble();
                                System.out.println("Enter elite price");
                                double elite_price = sc.nextDouble();
                                for (Category category : categories) {
                                    for (Product product : category.products.keySet()) {
                                        if (product.proID == p1ID) {
                                            for (Product product1 : category.products.keySet()) {
                                                if (product1.proID == p2ID) {
                                                    Giweaway giveaway = new Giweaway(product, product1);
                                                    admin.giveaway(allgiweaways, giveaway, normal_price, prime_price, elite_price);
                                                }
                                            }
                                        }
                                    }
                                }

                            } else if (choice2 == 7) {
                                break;
                            }
                        }
                    }
                }

            } else if (choice1 == 2) {

                System.out.println("Explore the Product Catalog");
                for(Category category : categories){
                    System.out.println(category.name);
                    for(Product product : category.products.keySet()){
                        System.out.println(product.name);
                        System.out.println(product.price);
                    }
                }
            } else if (choice1 == 3) {
                System.out.println("Show Available Deals");
                for(Giweaway giweaway: allgiweaways.keySet()){
                    System.out.println(giweaway.p1.name);
                    System.out.println(giweaway.p2.name);
                    System.out.println("Normal price: "+allgiweaways.get(giweaway).get(0));
                    System.out.println("Prime price: "+allgiweaways.get(giweaway).get(1));
                    System.out.println("Elite price: "+allgiweaways.get(giweaway).get(2));
                }






            } else if (choice1 == 4) {
                while(true){
                    System.out.println("1) Sign Up \n 2) Sign In \n 3) Exit");
                    int choice2 = sc.nextInt();
                    if(choice2==1){
                        System.out.println("Enter name");
                        String name = sc.next();
                        System.out.println("Enter password");
                        String password = sc.next();
                        System.out.println("Enter email");
                        String email = sc.next();
                        System.out.println("Enter phone number");
                        String phone_number = sc.next();
                        System.out.println("Enter age");
                        int age = sc.nextInt();

                        Customer customer = new Customer(name, age, phone_number, email,password);
                        customers.add(customer);
                    }
                    else if(choice2==2) {
                        System.out.println("Enter username");
                        String username = sc.next();
                        System.out.println("Enter password");
                        String password = sc.next();
                        for (Customer customer : customers) {
                            if (customer.name.equals(username) && customer.password.equals(password)) {

                            while (true) {
                                System.out.println("1) Browse products 2) Browse deals 3)Add products to cart 4) Add products in deal to cart 5)view coupon 6) check balance 7) view cart 8) empty cart 9) checkout 10)upgrade status 11)add to wallet 12) exit");
                                int choice3 = sc.nextInt();
                                if (choice3 == 1) {
                                    System.out.println("Browse Products");
                                    for (Category category : categories) {
                                        System.out.println(category.name);
                                        for (Product product : category.products.keySet()) {
                                            System.out.println(product.name);
                                            System.out.println(product.price);
                                        }
                                    }
                                } else if (choice3 == 2) {
                                    System.out.println("Browse Deals");
                                    customer.browsedeals(allgiweaways);

                                } else if (choice3 == 3) {
                                    System.out.println("Add products to cart");
                                    System.out.println("Enter product ID");
                                    double proID = sc.nextDouble();
                                    System.out.println("Enter quantity");
                                    int quantity = sc.nextInt();
                                    for(Category category: categories){
                                        for(Product product: category.products.keySet()){
                                            if(product.proID==proID){
                                                customer.addToCart( product, quantity);
                                            }
                                        }
                                    }

                                } else if (choice3 == 4) {
                                    System.out.println("Add products in deal to cart");
                                    System.out.println("Enter product 1 ID");
                                    double p1ID = sc.nextDouble();
                                    System.out.println("Enter product 2 ID");
                                    double p2ID = sc.nextDouble();
                                    System.out.println("Enter quantity");
                                    int quantity = sc.nextInt();
                                    for (Giweaway giweaway : allgiweaways.keySet()) {
                                        if (giweaway.p1.proID == p1ID && giweaway.p2.proID == p2ID) {
                                            customer.addToGiweawayCart( giweaway, quantity);
                                        }
                                    }
                                } else if (choice3 == 5) {
                                    for(int i=0;i<customer.Coupons.size();i++){
                                        System.out.println(customer.Coupons.get(i));
                                    }

                                } else if (choice3 == 6) {
                                    System.out.println("Check Balance");
                                    customer.viewWallet();
                                } else if (choice3 == 7) {
                                    System.out.println("View Cart");
                                    customer.viewCart();
                                } else if (choice3 == 8) {
                                    System.out.println("Empty Cart");
                                    customer.cart.clear();
                                } else if (choice3 == 9) {
                                    customer.checkout(alldiscounts,categories,allgiweaways);
                                    System.out.println("Checkout");
                                    //customer.EmptyCart();
















                                } else if (choice3 == 10) {
                                    System.out.println("Upgrade Status");
                                    System.out.println("Enter status");
                                    String status = sc.next();
                                    if (status.equals("Elite")) {
                                        customer.upgradeStatus("Elite");
                                        double temp= customer.wallet;
                                        customer = new EliteCustomer(customer.name, customer.age, customer.phonenumber, customer.email_ID, customer.password);
                                        customer.wallet=temp;
                                    } else if (status.equals("Prime")) {

                                        customer.upgradeStatus("Prime");
                                        double temp= customer.wallet;
                                        customer = new PrimeCustomer(customer.name, customer.age, customer.phonenumber, customer.email_ID, customer.password);
                                        customer.wallet=temp;
                                    }
                                    //System.out.println(customer.status);

                                } else if (choice3 == 11) {
                                    System.out.println("Add to wallet");
                                    System.out.println("Enter amount");
                                    int amount = sc.nextInt();
                                    customer.addMoney(amount);
                                } else if (choice3 == 12) {
                                    break;
                                }
                            }
                            }
                        }
                    }
                    else if(choice2==3){
                        break;
                    }





                }
            } else if (choice1 == 5) {
                break;
            }


        }
    }
}
