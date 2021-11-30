package blockbuster;

abstract class CouponTypeDecorator implements Coupon {
    public double _totalPrice;
    public double _frequentRenterPoints;
    public double _minimumRentalPrice;

    protected Coupon tempCoupon;

    public CouponTypeDecorator(Coupon newCoupon, double totalPrice, double minimumRentalPrice) {
        tempCoupon = newCoupon;
        _totalPrice = totalPrice;
        _minimumRentalPrice = minimumRentalPrice;
    }

    public String getCouponType() { return tempCoupon.getCouponType(); }

    public double couponModifiedPrice(double frequentRenterPoints) { return tempCoupon.couponModifiedPrice(frequentRenterPoints); }

}
