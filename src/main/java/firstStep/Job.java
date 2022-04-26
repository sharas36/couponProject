package firstStep;

import DAO.DAOCoupons.CouponsDBDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Job extends Thread {

    private ConnectionPool connectionPool = ConnectionPool.getInstanse();
    private List<Coupon> coupons = new ArrayList<>();
    private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();


    @Override
    public void run() {
        connectionPool.getConnection();
        try {
            coupons = couponsDBDAO.getAllCoupons();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                for (Coupon coupon : coupons) {
                    if (expireCheck(coupon.getEndDate())) {
                        couponsDBDAO.deleteCoupon(coupon.getCouponId());
                    }
                }
                sleep(1000 * 60 * 60);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public boolean expireCheck(Date exp) {
        Date current = new Date(System.currentTimeMillis());
        return exp.getTime() <= current.getTime();
    }
}
