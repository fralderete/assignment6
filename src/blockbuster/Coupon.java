package blockbuster;

/*      a. A coupon for a rental that allows 50% off the entire rental price.
        b. A coupon for a rental that takes 10$ off for a total rental of more than 50$.
        c. A mechanism to have 10 frequent rental points for a free movie.*/

public interface Coupon {
    public double totalPrice = 0;
    public double frequentRenterPoints = 0;
    public double tenDollarOffThreshold = 50.00;
    public int freeMovieThreshold = 10;

    void writeData();

    public String getCouponType();

    public double couponModifiedPrice(double doubleFRP);

}
