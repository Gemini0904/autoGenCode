import java.sql.Connection;
import java.sql.DriverManager;

public final class DBUtils {
	
	public static final String URL = "jdbc:mysql://192.168.2.250:3306/dev_izejin_branches?useUnicode=true&amp;characterEncoding=UTF-8";
	public static final String USER_NAME = "izejin";
	public static final String PASSWORD = "qeadzc132";
	
	public static Connection getConn()throws Exception{
		Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		return conn;
	}
	
}
