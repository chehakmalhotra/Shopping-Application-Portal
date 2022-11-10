public interface Discount {
    public double member_specific_discount(double product_price);
    public void get_coupon(double totalprice);
    public double use_coupon(double product_price);




}
