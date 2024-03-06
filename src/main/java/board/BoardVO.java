package board;

import java.util.Date;

public class BoardVO{
	
	//변수 생성
	private int num;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt;
	
	//기본 생성자
	public BoardVO() {
		
	}
	
	//필드 생성자
	public BoardVO(int num, String title, String writer, String content, Date regdaDate, int cnt) {
	
		super();//부모 클래스 생성자 호출
		this.num = num;
		this.title = title;
		this.writer = writer; // writer=작가
		this.content = content; //content=내용
		this.regdate = regdaDate;
		this.cnt = cnt;
		
	}
	
	//Getter, Setter
	public int getNum() {
		return num;
	}
	
	public void  SetNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public int getCnt() {
		return cnt;
	}
	
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	//toString - 객체가 가지고 있는 정보나 값을 문자열로 만들어 리턴하는 메소드
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + "]";
	}
	
	
}




