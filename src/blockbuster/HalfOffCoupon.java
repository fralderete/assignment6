package blockbuster;

public class HalfOffCoupon extends CouponTypeDecorator {
    public HalfOffCoupon(Coupon newCoupon, double totalPrice, double minimumRentalPrice) {
        super(newCoupon, totalPrice, minimumRentalPrice);

    }

    @Override
    public void writeData() {
        _totalPrice = totalPrice;
    }

    @Override
    public String getCouponType() {
        return ProgramProperties.HALF_OFF_COUPON; // placeholder

    }

    public double couponModifiedPrice(double frequentRenterPoints) {
        // System.out.println("You have applied a half off coupon to your purchase!");
        _totalPrice = _totalPrice / 2;
        return _totalPrice;

    }

}
