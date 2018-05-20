package SQL;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

	public static void createNewUser(String name, String filename, String status, String sex, int age, String state)
			throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:MiniDB.db";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url);
			String sql = "insert into Profiles (name, image, status, sex, age, state) values (?,?,?,?,?,?);";
			PreparedStatement pstmn = con.prepareStatement(sql);
			pstmn.setString(1, name);
			pstmn.setBytes(2, readFile(filename));
			pstmn.setString(3, status);
			pstmn.setString(4, sex);
			pstmn.setInt(5, age);
			pstmn.setString(6, state);

			pstmn.execute();
			con.commit();

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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return baos != null ? baos.toByteArray() : null;
	}

	public static void main(String[] args) throws ClassNotFoundException {

		createNewUser("Bilbo Baggins", "data/Bilbo.jpg", "retired", "M", 50, "Shire");
	}

}
