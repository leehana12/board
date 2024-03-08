# Board

## 목차
1. 프로젝트 소개
2. 기술 스텍
3. 구현 기능
4. 구현 기능 둘러 보기
5. 배운점 & 아쉬운점

<br>
<br>

## :clipboard: 프로젝트 소개

### 개요
* 프로젝트 이름 : MVC1 게시판



### 설명
* CRUD를 활용하여 mvc1패턴의 게시판을 만들어 JSP의 대한 이해도를 높이기 위한 프로젝트.
* **CRUD**란?  
  * **C** - Create
  * **R** - Read 
    1. 목록보기
    2. 상세보기
  * **U** - Update
  * **D** - Delete  

<br>
<br>

***
## :wrench: 주요기능 설명


### 1.insert() 메소드
* 사용자에게 입력받은 데이터를 삽입하는 기능을 갖는 메소드.

### 2. selectAll() 메소드
* 전체 쿼리(리스트)를 역순으로 출력해주는 메소드.

### 3. selectOne() 메소드
* 상세조회를 위해 사용자가 선택한 제목을 통해 글번호로 조회 할수 있게 해주는 메소드.

### 4. update() 메소드
* 특정 번호에 해당하는 게시물을 가져와 내용을 수정해 데이터베이스에 업데이트 하는 메소드.

### 5.updateCnt() 메소드
* 카운터 증가 메소드. 조회수 개수 증가와 데이터를 삭제 할 때 사용.

### 6. delete() 메소드
* 게시글 번호를 매개변수로 받아 해당 번호의 게시글을 삭제하는 메소드 

<br>
<br>

***
## :wrench: 구현기능 둘러보기

### 1. 게시글 목록 보기
![image](https://github.com/leehana12/board/assets/154123779/6aca07a2-976d-484c-b094-59db16687e13 )

### 2. 게시글 수정 하기
**수정전**
![image](https://github.com/leehana12/board/assets/154123779/d551391a-d81c-4b8b-b081-806b0f483d5a )
<br>
![image](https://github.com/leehana12/board/assets/154123779/c1810a28-9c34-4555-af8a-f15d2707068c)<br>
**수정 후**
![image](https://github.com/leehana12/board/assets/154123779/cc451b08-5acb-4330-81ec-c6ca14fcb0df)



