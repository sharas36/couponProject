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
    boolean quit = true;

    @Override
    public void run() {

        while (quit) {

            try {
                couponsDBDAO.filterExpiredCoupon();
                sleep(1000 * 60 * 60 * 24);
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    public void quitProgram() {
        this.quit = false;
    }


}


