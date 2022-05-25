package firstStep;

import DAO.DAOCoupons.CouponsDBDAO;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Job extends Thread {

    private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
    boolean quite = true;

    @Override
    public void run() {

        while (quite) {

            try {
                couponsDBDAO.filterExpiredCoupon();
                sleep(1000 * 60 * 60 * 24);
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    public void quiteProgram() {
        this.quite = false;
    }


}


