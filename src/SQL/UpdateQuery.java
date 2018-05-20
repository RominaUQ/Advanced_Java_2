package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Savran Aleksei Not finished
 */
public class UpdateQuery {
	public static void updateProfile(String name, byte image, String status, String sex, int age, String state)
			throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:MiniDB.db";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url);
			String sql = "insert into Profiles (name, image, status, sex, age, state) values (?,?,?,?,?,?);";
			PreparedStatement pstmn = con.prepareStatement(sql);
			pstmn.setString(1, name);
			pstmn.setByte(2, image);
			pstmn.setString(3, status);
			pstmn.setString(4, sex);
			pstmn.setInt(5, age);
			pstmn.setString(6, state);

			ResultSet rs = pstmn.executeQuery();
			con.commit();
			while (rs.next()) {
				System.out.println(rs.getString(1) + rs.getString(2) + rs.getInt(3) + rs.getString(4));
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
}
