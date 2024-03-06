package board;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.JdbcUtil;

public class BoardDAO {

	private JdbcUtil ju;
	
	public BoardDAO() {
		ju = JdbcUtil.getInstance();
	}
	
	//삽입(c)
	public int insert(BoardVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into board(num, title, writer, content, regdate, cnt) values(board_seq.nextval, ?, ?, ?, sysdate, 0)";
		int ret = -1;
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			ret = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
		
	}
	
	//조회(R)- R은 Read
	public List<BoardVO> selectAll(){
		
		Connection con = null;
		// 에러 떠서 형식 변환함. 형식 불일치: java.sql.statement에서 java.beans.statement로 변환할 수 없습니다.
		// java.beans.Statement가 아닌 jdbc를 이용한 데이터 베이스와 상호 작용을 위해서는 java.sql.Statement를 사용한다.
		java.sql.Statement stmt = null; 
		ResultSet rs = null;
		String query = "select num, title, writer, content, regdate, cnt from board";
		List<BoardVO> ls = new ArrayList<>();
		try {
			con = ju.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			System.out.println("one");
			while (rs.next()) {
				BoardVO vo = new BoardVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						new Date(rs.getDate(5).getTime()),
						rs.getInt(6)
						);
				ls.add(vo);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ls;
	}//selectAll
	
	//상세조회(R)
	public BoardVO selectOne(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select num, title, writer, content, regdate, cnt from board where num = ?";
		BoardVO vo = null;
		try {
			//ju = jdbcUtil
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new BoardVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						new Date(rs.getDate(5).getTime()),
						rs.getInt(6)
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return vo;
	}//selectOne
	
	//수정(U) - 특정 번호에 해당하는 게시물을 가져와 내용 수정 데이터베이스 업데이트 
	//번호를 사용해서 조회 하기 때문에 int 타입을 사용
	// BoardVO 객체를 매개변수로 받아 게시글을 업데이트하는 메서드.
	public int update(BoardVO vo) {
		Connection con = null; // 데이터베이스와의 연결을 나타내는 Connection객체 초기화
		PreparedStatement pstmt = null; //SQL 문을 실행하기 위한 PreparedStatement 객체 초기화
		String query = "update board set title=?, writer=?, content=? where num=?";//SQL 업데이트 쿼리 정의
		int ret = -1; // 업데이트 결과를 나타내는 변수 초기화. 업데이트 작업이 실패하거나 예외가 발생하면 -1값 유지. 
		try {
			// jdbcUtil 클래스의 getConnection() 메서드를 사용하여 데이터베이스와의 연결을 설정.
			con = ju.getConnection(); //커넥션을 가져옴
			
			//연결된 데이터베이스를 통해 준비된 PreparedStatement를 생성하여 쿼리를 준비
			pstmt = con.prepareStatement(query);
			
			// 쿼리의 매개변수에 값을 설정 - 각 ?에 대응하는 값을 BoardVO 객체에서 가져와 대입
			pstmt.setString(1, vo.getTitle()); // 첫번쨰 물을표에 vo의 제목을 설정
			pstmt.setString(2, vo.getWriter()); // 두번쨰 물음표에 vo의 작성자를 설정
			pstmt.setString(3, vo.getContent()); // 세번쨰 물음표에 vo의 내용을 설정
			pstmt.setInt(4, vo.getNum()); // 네번쨰 물음표에 vo의 번호를 설정
			
			ret = pstmt.executeUpdate(); // 쿼리를 실행하고 업데이트된 행의 수를 반환. 반환된 값은 ret 변수에 저장
		} catch (Exception e) {
			e.printStackTrace(); // SQL 예외 발생시 에러 메시지 출력
		} finally {
			//리소스 해제 = close()메서드를 이용하여 메모리 누수방지,시스템 자원 효율적 관리,컴퓨터 성능 최적화
			//사용한 자원 애제
			//PreparedStatement와 Connection 객체가 null이 아닌 경우 각각 close()메서드를 사용하여 자원 해제
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 메서드의 반환값으로 업데이트된 행의 수를 반환.
		return ret;
	}
	
	//삭제(D) - 게시글 번호를 매개변수로 받아 해당 번호의 게시글을 삭제하는 메서드
	public int delete(int num) {
		Connection con = null; //데이터베이스와의 연결을 나타내는 Connection 객체 초기화
		PreparedStatement pstmt = null; //SQL 문을 실행하기 위한 PreparedStatement 객체 초기화
		String query = "delete from board where num=?"; //SQL 삭제 쿼리 정의
		int ret = -1; // 삭제 결과 변수 초기화. -1은 초기값으로 설정, 실패 했을 떄 유지
		
		try {
			//데이터 베이스와의 연결 설정
			con = ju.getConnection();
			
			//연결된 데이터베이스를 통해 PreparedStatement 객체를 생성하여 쿼리 실행 준비
			pstmt = con.prepareStatement(query);
			
			//PreparedStatement 객체에 setInt()메서드를 사용하여 쿼리의 매개변수를 설정.
			// ?에 해당하는 값으로 삭제할 게시글의 번호를 설정
			pstmt.setInt(1, num);
			
			// 준비된 쿼리를 실행, 삭제된 행의 수를 반환
			// 반호나된 값은 ret 변수에 저장
			ret = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();// 예외 발생시 에러 메세지 출력
		} finally {
			//사용한 자원 해제
			// PreparedStatement와 Connection 객체가 null값이 아닌경우 close()메서드를 호출하여 자원해제
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(); // SQL에러 발생시 에러 메세지 출력
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 메서드의 반환값으로 삭제된 행의 수를 반환
		return ret;
	}
	
}





