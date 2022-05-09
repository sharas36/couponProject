package firstStep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.Thread.sleep;

public class ConnectionPool {
    private static ConnectionPool instanse = new ConnectionPool();

    private static final String DOMAIN_STRING = "localhost";// 127.0.0.1
    private static final String DB_NAME = "couponsproject";
    private static final String USER_NAME = "student";
    private static final String PASSWORD = "student";
    private static final String CONNECTION_STRING = "jdbc:mysql://" + DOMAIN_STRING + "/" + DB_NAME + "?user="
            + USER_NAME + "&password=" + PASSWORD;
    private Stack<Connection> connectionPool = new Stack<>();

    // CONNECTION_STRING:
    // jdbc:mysql://localhost:3306/example/user=root&password=1234

    private ConnectionPool() {
        super();
        try {
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to init DatabaseManager: " + e);
        }
    }

    public static ConnectionPool getInstanse() {
        return instanse;
    }

    private void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("JDBC driver loaded successfully!");
        System.out.println("CONNECTION_STRING: " + CONNECTION_STRING);
        for (int i = 0; i < 5; i++) {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING);
            this.connectionPool.push(connection);
        }
        System.out.println("Connected to database " + DB_NAME);
        System.out.println("We have " + connectionPool.size() + " connections");
    }

    public synchronized Connection getConnection() {
        while(true){
            if(connectionPool.size() > 0){
                return connectionPool.pop();
            }
            else {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized boolean restoreConnection(Connection connection) {

        return connectionPool.add(connection);

    }

    public void closeAllConnections() throws SQLException { ////check all connections not in use
        for (Connection connection : connectionPool) {
            connection.close();
        }
    }
    public int getConnectionPoolSize(){
        return connectionPool.size();
    }
}
