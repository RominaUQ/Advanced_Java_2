package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Aleksei Savran	
 * This class is responsible for establishing the database connection	
 */
public class DBconnection {
//	public static void connectDB() {
//		Connection con = null;		
//		try {
//			String url = "jdbc:sqlite:MiniDB.db";
//			con = DriverManager.getConnection(url);
//			
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		} finally {
//			try {
//				if (con != null) {
//					con.close();
//				}
//			} catch (SQLException e2) {
//				System.out.println(e2.getMessage());
//			}
//		}
//	}
	

	
	public DBconnection (String url) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
