package firstStep;

import DAO.DAOCoupons.CouponsDBDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Job extends Thread {

    private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();


    @Override
    public void run() {
        while (true) {
            try {
                if(couponsDBDAO.isExpired()){
                    System.out.println("All coupons are valid");
                }
                else{
                    System.out.println("All expired coupons are deleted");
                }
                sleep(1000 * 60 *60 * 24);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
