package SQL;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Savran Aleksei This class is responsible for queries related to the
 *         creating new data (new users and their details)
 */
public class CreateQueries {
	private static FileInputStream fis;

	public static void createNewUser(String name, String status, String sex, int age, String state) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:MiniDB.db";
			con = DriverManager.getConnection(url);

			String sql = "insert into Profiles (name, image, status, sex, age, state) values (?,?,?,?,?,?);";
			
			PreparedStatement pstmn = con.prepareStatement(sql);
			pstmn.setString(1, name);
			pstmn.setBytes(2, readFile("src/resources/noimagefound.jpg"));
			pstmn.setString(3, status);
			pstmn.setString(4, sex);
			pstmn.setInt(5, age);
			pstmn.setString(6, state);

			pstmn.execute();
			con.commit();

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
	}

	public static Boolean addRelation(String name, String relationName, String relation) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:MiniDB.db";

			con = DriverManager.getConnection(url);
			String sql = "insert into relations (profile1, profile2, relation) values (?,?,?);";
			PreparedStatement pstmn = con.prepareStatement(sql);
			pstmn.setString(1, name);
			pstmn.setString(2, relationName);
			pstmn.setString(3, relation);
			pstmn.execute();
			con.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		}
		return true;
	}

	private static byte[] readFile(String file) {
		ByteArrayOutputStream baos = null;
		try {
			File f = new File(file);
			fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();
			for (int len; (len = fis.read(buffer)) != -1;) {
				baos.write(buffer, 0, len);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return baos != null ? baos.toByteArray() : null;
	}
}
