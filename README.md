# Java_Web (Dynamic Web Project)
1. tomcat download <br />
2. 이클립스와 톰캣 연동 : <Window - Show View - Servers> --> 하단 Tomcat Server 클릭 --> 톰캣 포트번호를 8080에서 80으로 변경 --> Start<br />

# JAR 파일, 빌드 경로에 추가하기
이클립스에서 JSP 실행을 위해 JAR 파일을<br />
웹 애플리케이션의 WEB-INF/lib 폴더에 붙여넣기<br />

# Servlet CRUD
- Create : html 폼 - > Servlet -> DAO ->users 저장 <br />
- Read : html에서 요청 -> Servlet -> DAO -> users <br />
- Update : html 폼 -> 새 데이터 입력 -> Servlet -> DAO -> 수정 <br />
- Delete : html에서 삭제 요청 -> Servlet -> DAO -> 삭제 <br /><br />

#### 수정 > 암호수정용 폼(아이디는 뜨고, 암호는 비워둠) > 새 암호 입력 > 저장 > DB저장<br />
1. 수정 버튼 생성 > 누르면 > 수정폼(editPwd.jsp) 출력<br />
2. 저장 버튼을 누루면 > 해당 이용자의 암호가 서버에서 변경됨<br />
3. 성공/실패 메시지(jsp 없이 수정폼에 성공/실패 메시지 표시)<br />

# jQuery
#### jQuery download <br />
jQuery CDN > https://releases.jquery.com > jQuery 3.x >  minified <br />

#### jsp 파일에 jQuery 추가하기
*ctrl+cv <br />
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<br /><br />

이용자 정보 입력 기능<br />
1. [이용자 정보 추가] > 입력 폼 > [저장] > 성공/실패 > 상세정보 표시<br />
2. 목록보기 화면에 버튼 추가  > userinput.jsp > AJAX 요청 > 성공/실패 > userDetail.jsp<br />
3. cmd=addForm, cmd=add (dao.add(user)), cmd=detail<br />
<br /> 
Service 레이어 도입 (UserService 클래스)<br />
로그인 이용자만 데이터 변경 가능하게<br />

Pet 관리 시스템 종합 실습<br />
<br />
#### Java Web Scope(영역 오브젝트)
1. 이용자가 속하게 되는 논리적인 영역<br />
2. page 영역: 한 페이지(서블릿) 안의 영역<br />
3. request 영역: 이용자가 다른 jsp, servlet으로 forward될 때 원래 요청된 페이지와 forward된 페이지<br />
4. session 영역: 이용자가 지나가는 모든 jsp, servlet<br />
5. application 영역: 한 사이트에 있는 모든 jsp, servlet<br />
<br />
영역 오브젝트의 공통 메소드<br />
1. obj.setAttribute("key", "value"); // 영역 오브젝트에 key, value를 저장<br />
2. obj.getAttribute("key"); // 영역 오브젝트에 저장된 key를 사용하여 value를 구한다<br />

#### json simple jar 다운로드 / 추가
json-simple-1.1.1.jar<br />
JAR 파일을<br />
웹 애플리케이션의 WEB-INF/lib 폴더에 붙여넣기<br />
