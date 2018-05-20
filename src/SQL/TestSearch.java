package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Savran Aleksei ignore this class for test purpose
 */
public class TestSearch {
public static void userSearch(String name) {
		
		String url = "jdbc:sqlite:MiniNetDB";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url);
			String sql = "Select name, age, status"
						+ "from users"
						+ "where Users.name =" + name;";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("name") +
				rs.getInt("age") +
				rs.getString("status"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()); 
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage()); 
				}
			}
		}
	}

	public static void main(String[] args) {
		String name = "John Smeet";
		userSearch(name);
	}

}
