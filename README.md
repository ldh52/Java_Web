# Java_Web
1. tomcat download <br />
2. 이클립스와 톰캣 연동 : <Window - Show View - Servers> --> 하단 Tomcat Server 클릭 --> 톰캣 포트번호를 8080에서 80으로 변경 --> Start<br />

# JAR 파일, 빌드 경로에 추가하기
이클립스에서 JSP 실행을 위해 JAR 파일을<br />
웹 애플리케이션의 WEB-INF/lib 폴더에 붙여넣기<br />

# *.java, *.jsp <br />
1. java <br />
└ src/main/java/com/test/sku/HelloServlet.java <br />
└ src/main/java/com/test/sku/Gugu.java <br />
└ src/main/java/com/test/sku/servlet/GuguServlet.java <br />
└ src/main/java/com/test/sku/servlet//UserServlet.java <br />
└ src/main/java/com/test/sku/servlet/User.java <br />
└ src/main/java/com/test/sku/servlet/UserDAO.java <br />

2. jsp <br />
└ src/main/webapp/jsp/hello.jsp <br />
└ src/main/webapp/jsp/gugu.jsp  <br />
└ src/main/webapp/jsp/loginForm.jsp <br />
└ src/main/webapp/jsp/loginResult.jsp <br />
└ src/main/webapp/jsp/UserDetail.jsp <br />
└ src/main/webapp/jsp/editPwd.jsp <br />

# Servlet CRUD
- Create : html 폼 - > Servlet -> DAO ->users 저장 <br />
- Read : html에서 요청 -> Servlet -> DAO -> users <br />
- Update : html 폼 -> 새 데이터 입력 -> Servlet -> DAO -> 수정 <br />
- Delete : html에서 삭제 요청 -> Servlet -> DAO -> 삭제 <br /><br />
// 내용은 소스코드 보면서 확인<br />
<a href="user?cmd=detail&uid=smith">smith</a><br />
<%= u.getUid() %><br />
<a href="user?cmd=detail&uid=<%= u.getUid() %>"><%= u.getUid() %></a><br />
//<br />
<br />
수정 > 암호수정용 폼(아이디는 뜨고, 암호는 비워둠) > 새 암호 입력 > 저장 > DB저장<br />
1. 수정 버튼 생성 > 누르면 > 수정폼(editPwd.jsp) 출력<br />
2. 저장 버튼을 누루면 > 해당 이용자의 암호가 서버에서 변경됨<br />
3. 성공/실패 메시지(jsp 없이 수정폼에 성공/실패 메시지 표시)<br /><br /><br />

# jQuery <br />
jQuery download <br />
jQuery CDN > https://releases.jquery.com > jQuery 3.x >  minified <br /><br />
*ctrl+cv <br />
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
