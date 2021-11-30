package blockbuster;

public class TenDollarsOffCoupon extends CouponTypeDecorator {

    public TenDollarsOffCoupon(Coupon newCoupon, double totalPrice, double minimumRentalPrice) {
        super(newCoupon, totalPrice, minimumRentalPrice);
    }

    @Override
    public void writeData() {
        _totalPrice = totalPrice;
    }

    @Override
    public String getCouponType() {
        return ProgramProperties.TEN_USD_OFF_COUPON;
    }

    public double couponModifiedPrice(double frequentRenterPoints) {
        if ( _totalPrice > tenDollarOffThreshold) {
            // System.out.println("You qualified for ten dollars off of your purchase.");
            _totalPrice = _totalPrice - 10.00;

        }

        return _totalPrice;

    }

}
