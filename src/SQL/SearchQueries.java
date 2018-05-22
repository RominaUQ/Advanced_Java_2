package SQL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import gui.*;

/**
 * @author Savran Aleksei This class implements search function
 */
public class SearchQueries {
	public static Set<Relation> getRelationsForUser(String name) {
		Connection con = null;
		Set<Relation> relations = new HashSet<Relation>();
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:MiniDB.db";

			con = DriverManager.getConnection(url);

			String getRelations = "SELECT profile2, relation FROM relations WHERE profile1 = ?;";
			PreparedStatement pstm = con.prepareStatement(getRelations);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				String relationName = rs.getString(1);
				String relation = rs.getString(2);
				relations.add(new Relation(relationName, relation));
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
		return relations;
	}

	public static Profile userSearch(String name) {

		Profile pr = null;
		String url = "jdbc:sqlite:MiniDB.db";
		Connection con = null;
		FileOutputStream fos = null;
		try {
			con = DriverManager.getConnection(url);
			String sql = "Select * from Profiles where name = ? order by name";

			PreparedStatement pstmn = con.prepareStatement(sql);
			pstmn.setString(1, name);

			ResultSet rs = pstmn.executeQuery();

			String filename = "temp.jpg";
			File f = new File(filename);
			fos = new FileOutputStream(f);

			while (rs.next()) {
				String prName = rs.getString(2);
				String prStatus = rs.getString(4);
				String prSex = rs.getString(5);
				Integer prAge = rs.getInt(6);
				String prState = rs.getString(7);
				if (prAge > 16) {
					pr = new Adult(prName, "temp.jpg", prStatus, prSex, prAge, prState);
				}
				if (prAge > 2 && prAge < 17) {
					pr = new Child(prName, "temp.jpg", prStatus, prSex, prAge, prState, null, null);
				}
				if (prAge < 3) {
					pr = new YoungChild(prName, "temp.jpg", prStatus, prSex, prAge, prState, null, null);
				}

			}

			while (rs.next()) {
				InputStream is = rs.getBinaryStream("image");
				byte[] buffer = new byte[1024];
				while (is.read(buffer) > 0) {
					fos.write(buffer);
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
		return pr;
	}
}