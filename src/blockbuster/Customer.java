package blockbuster;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String _name;
    private int _age;
    private double _totalCharge;
    int rentalPriceListSize;
    double minimumRentalPrice;
    List<Double> rentalPrices = new ArrayList<Double>();

    private List<Rental> rentals = new ArrayList<>();
    // added to include age, previous method didn't accept an additional parameter
    public void setName (String name) {
        _name = name;
    }

    public void setAge (int age) {
        _age = age;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return _name;
    }

    public int getAge() { return _age; }

    public String htmlRentalReceipt(Transaction transaction) {
        boolean _multipleGenre = transaction.getMultipleGenre();
        boolean _newMovie = transaction.getNewMovie();


        String result = "\n<table><h1>Rental record for <em>" + getName() + "</em></h1>\n";
        for (Rental rental : rentals) {
            result += "\t<tr><td>" + rental.getMovie().getTitle() + "</td><td>" + ": $"
                    + rental.getRentalPrice() + "0</td></tr>\n";

            rentalPrices.add(rental.getRentalPrice());

        }

        result += "<p>You earned <em>" + getTotalFrequentRenterPoints()
                + "</em> frequent renter points</p>\n";

        minimumRentalPrice = rentalPrices.get(0);
        rentalPriceListSize = rentalPrices.size();

        // determining the least expensive rental for free movie coupon
        for (int i = 1; i < rentalPriceListSize; i++) {
            if (rentalPrices.get(i) < minimumRentalPrice) {
                minimumRentalPrice = rentalPrices.get(i);
            }
        }

        if (_multipleGenre && _newMovie) {
            transaction.setDoubleFRP(getTotalFrequentRenterPoints());
            result += "<p>You have qualified for double bonus renter points due to multiple genre rentals. \nYou have <em>" + transaction.getDoubleFRP() + "</em> total frequent renter points.</p>\n";
            transaction.setDoubleFRP(transaction.getDoubleFRP());
            result += "<p>You have qualified for double bonus renter points due to your age and new movie rental. \nYou have <em>" + transaction.getDoubleFRP() + "</em> total frequent renter points.</p>\n";
        }

        if (_multipleGenre && !_newMovie) {
            transaction.setDoubleFRP(getTotalFrequentRenterPoints());
            result += "<p>You have qualified for double bonus renter points due to multiple genre rentals. \nYou have <em>" + transaction.getDoubleFRP() + "</em> total frequent renter points.</p>\n";
        }

        if (_newMovie && !_multipleGenre) {
            transaction.setDoubleFRP(getTotalFrequentRenterPoints());
            result += "<p>You have qualified for double bonus renter points due to your age and new movie rental. \nYou have <em>" + transaction.getDoubleFRP() + "</em> total frequent renter points.</p>\n";

        }

        result += "\n<p>Amount owed is <em>$" + getTotalCharge() + "</em></p>\n";

        setTotalCharge(getTotalCharge());

        Coupon halfOffCoupon = new HalfOffCoupon(null, _totalCharge, minimumRentalPrice);
        setTotalCharge(halfOffCoupon.couponModifiedPrice(transaction.getDoubleFRP()));
        result += "<p>Your total after " + halfOffCoupon.getCouponType() + " is: <em>$" + _totalCharge + "</em></p>\n";

        Coupon tenDollarsOffCoupon = new TenDollarsOffCoupon(null, _totalCharge, minimumRentalPrice);
        setTotalCharge(tenDollarsOffCoupon.couponModifiedPrice(transaction.getDoubleFRP()) );
        result += "<p>Your total after " + tenDollarsOffCoupon.getCouponType() + " is: <em>$" + _totalCharge + "</em></p>\n";

        Coupon freeMovieCoupon = new FreeMovieCoupon(null, _totalCharge, minimumRentalPrice);
        setTotalCharge(freeMovieCoupon.couponModifiedPrice(transaction.getDoubleFRP()));
        result += "<p>Your total after " + freeMovieCoupon.getCouponType() + " is: <em>$" + _totalCharge + "</em></p></table>\n";

        return result;
    }

    public double getTotalCharge() {
        double total = 0;
        for (Rental rental : rentals)
            total += rental.getRentalPrice();
        return total;
    }

    public void setTotalCharge(double totalCharge) {
        _totalCharge = totalCharge;

    }

    public  double getTotalFrequentRenterPoints() {
        double total = 0;
        for (Rental rental: rentals)
            total += rental.getFrequentRenterPoints();
        return total;
    }
}