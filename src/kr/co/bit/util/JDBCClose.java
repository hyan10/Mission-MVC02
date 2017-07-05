package kr.co.bit.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCClose {
	public static void close(Connection conn, Statement st) {
		
		if (st != null)
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
