package blockbuster;

public class CouponImplementor implements Coupon {
    public double _totalPrice;

    @Override
    public void writeData() {
        _totalPrice = totalPrice;
    }

    @Override
    public String getCouponType() {
        return "No coupon applied."; // placeholder
    }

    @Override
    public double couponModifiedPrice(double doubleFRP) {
        return _totalPrice;
    }

}
