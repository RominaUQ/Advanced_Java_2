package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import gui.*;

/**
 * @author Savran Aleksei This class implements method to show all users
 */
public class ShowAllUsers {
	public static Set<Profile> userShowAll() {
		Connection con = null;
		Set<Profile> profiles = new HashSet<>();

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:MiniDB.db";

			con = DriverManager.getConnection(url);
			String sql = "Select * from Profiles;";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int age = rs.getInt(6);
				if (age > 16) {
					profiles.add(new Adult(rs.getString(2), rs.getString(2) + ".jpg", rs.getString(4), rs.getString(5),
							rs.getInt(6), rs.getString(7)));
				}else if (age > 2) {
					profiles.add(new Child(rs.getString(2), rs.getString(2) + ".jpg", rs.getString(4), rs.getString(5),
							rs.getInt(6), rs.getString(7), null, null));
				}
				else {
					profiles.add(new YoungChild(rs.getString(2), rs.getString(2) + ".jpg", rs.getString(4), rs.getString(5),
							rs.getInt(6), rs.getString(7), null, null));
				}
			}

		} catch (Exception e) {
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
		return profiles;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		userShowAll();
	}

}
