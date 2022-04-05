package firstStep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
	private static ConnectionPool instanse = new ConnectionPool();

	private static final String DOMAIN_STRING = "localhost";// 127.0.0.1
	private static final String DB_NAME = "couponsproject";
	private static final String USER_NAME = "student";
	private static final String PASSWORD = "student";
	private static final String CONNECTION_STRING = "jdbc:mysql://" + DOMAIN_STRING + "/" + DB_NAME + "?user="
			+ USER_NAME + "&password=" + PASSWORD;
	private List<Connection> connectionPool = new ArrayList<Connection>();
	private List<Connection> connectionsInUse = new ArrayList<Connection>();

	// CONNECTION_STRING:
	// jdbc:mysql://localhost:3306/example/user=root&password=1234

	private ConnectionPool() {
		super();
		try {
			init();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
			this.connectionPool.add(connection);
		}
		System.out.println("Connected to database " + DB_NAME);
		System.out.println("We have " + connectionPool.size() + " connections");
	}

	public Connection getConnection() {
		Connection connection = connectionPool.get(connectionPool.size() - 1);
		connectionPool.remove(connectionPool.size() - 1);
		connectionsInUse.add(connection);
		return connection;
	}

	public boolean restoreConnection(Connection connection) {
		connectionPool.add(connection);
		return connectionsInUse.remove(connection);
	}

	public void closeAllConnections() throws SQLException {
		for (Connection connection : connectionPool) {
			connection.close();
		}
	}
}
