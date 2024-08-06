# Java_Web
1. tomcat download <br />
2. 이클립스와 연동 : <Window - Show View - Servers> --> 하단 Tomcat Server 클릭 --> 톰캣 포트번호를 8080에서 80으로 변경 --> Start<br />
# *.java, *.jsp <br />
1. java <br />
└ src/main/java/com/test/sku/Gugu.java <br />
└ src/main/java/com/test/sku/HelloServlet.java <br />
└ src/main/java/com/test/sku/servlet/GuguServlet.java <br />
2. jsp <br />
└ src/main/webapp/jsp/gugu.jsp  <br />
└ src/main/webapp/jsp/hello.jsp <br />
# Servlet CRUD
- Create : html 폼 - > Servlet -> DAO ->users 저장 <br />
- Read : html에서 요청 -> Servlet -> DAO -> users <br />
- Update : html 폼 -> 새 데이터 입력 -> Servlet -> DAO -> 수정 <br />
- Delete : html에서 삭제 요청 -> Servlet -> DAO -> 삭제 <br /><br />
<a href="user?cmd=detail&uid=smith">smith</a>
