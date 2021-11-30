package blockbuster;

public class FreeMovieCoupon extends CouponTypeDecorator {
    public FreeMovieCoupon(Coupon newCoupon, double totalPrice, double minimumRentalPrice) {
        super(newCoupon, totalPrice, minimumRentalPrice);

    }

    @Override
    public void writeData() {
        _totalPrice = totalPrice;
    }

    @Override
    public String getCouponType() {
        return ProgramProperties.FREE_MOVIE_COUPON;
    }

    public double couponModifiedPrice(double frequentRenterPoints) {
        if (frequentRenterPoints >= freeMovieThreshold) {
            // System.out.println("You have qualified for a free movie! You saved: $" + _minimumRentalPrice);
            _totalPrice = _totalPrice - _minimumRentalPrice;

        }

        return _totalPrice;

    }

}
