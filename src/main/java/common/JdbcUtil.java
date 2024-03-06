package common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


//DB연동을 위한 컨넥션풀 클래스
public class JdbcUtil {

	private static  JdbcUtil instance = new JdbcUtil();
	
	private static DataSource ds;//sql데이터소스로 import함.
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");//Oracle JDBC 드라이버를 로드
			System.out.println("드라이버 로딩 성공");
			InitialContext ctx = new InitialContext();//JNDI의 초기 컨텍스트를 생성
			Context envContext = (Context) ctx.lookup("java:comp/env");//JNDI 네임스페이스에서 "java:comp/env" 위치에 있는 환경 컨텍스트를 검색
			ds = (DataSource) envContext.lookup("jdbc/oracle");//"java:comp/env/jdbc/oracle" 위치에 있는 데이터 소스를 검색
			System.out.println("Connection Pool 생성!");
		} catch (ClassNotFoundException e) { // 클래스를 찾을 수 없는 경우 발생하는 예외
			e.printStackTrace();
		} catch (NamingException e) { //JNDI를 사용하여 네임스페이스에서 객체를 찾거나 바인딩할 때 발생할 수 있는 예외
			e.printStackTrace();
		}
	}
	
	private JdbcUtil() {}
	
	public static JdbcUtil getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		return ds.getConnection(); // 풀에서 커넥션 반환
	}
}
