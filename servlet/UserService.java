package com.test.sku.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserService 
{
   private HttpServletRequest request;
   private HttpServletResponse response;
   
   public UserService() {}

   public UserService(HttpServletRequest request, HttpServletResponse response) {
      this.request = request;
      this.response = response;
   }

   public String process()
   {
      String cmd = request.getParameter("cmd");
      String viewPath = null;
      
      if(cmd==null || cmd.equals("loginForm")) 
      {
         viewPath = "/jsp/loginForm.jsp";
      }
      else if(cmd.equals("login")) {
         //로그인 절차 수행
         String uid = request.getParameter("uid");
         String pwd = request.getParameter("pwd");
         
         User user = new User(uid,pwd);
         UserDAO dao = new UserDAO();
         boolean ok = dao.login(user);
         
         //로그인 통과한 이용자는 어떤 페이지로 이동하든지 로그인 여부를 확인할 수 있도록
         //영역 오브젝트에 로그인 성공 사실을 기억해놓는다
         if(오케이) {
            request.getSession().setAttribute("uid", uid);
         }
         //이용자에게 로그인 성공/실패 여부를 전달한다
         sendJSON("ok", ok+"");
         
      }else if(cmd.equals("addForm")) {
         viewPath = "/jsp/userInput.jsp";
      }
      else if(cmd.equals("add")) {
         if(!loginCheck()) {
            JSONObject jsObj = new JSONObject();
            jsObj.put("added", false);
            jsObj.put("cause", "로그인 이용자만 사용할 수 있습니다");
            jsObj.put("loginRequired", true);
            String js = jsObj.toJSONString();
            try {
               PrintWriter out = response.getWriter();
               out.print(js);
               out.flush();
               return null;
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
         String uid = request.getParameter("uid");
         String pwd = request.getParameter("pwd");
         boolean added = new UserDAO().add(new User(uid,pwd));
         sendJSON("added", added+"");
      }
      else if(cmd.equals("list")) {
         UserDAO dao = new UserDAO();
         List<User> list = dao.getList();
         request.setAttribute("list", list);
         viewPath = "/jsp/userList.jsp";
      }else if(cmd.equals("detail")) {
         String uid = request.getParameter("uid");
         UserDAO dao = new UserDAO();
         User u = dao.getDetail(uid);
         request.setAttribute("user", u);
         viewPath = "/jsp/userDetail.jsp";
      }else if(cmd.equals("edit")) {
         String uid = request.getParameter("uid");
         request.setAttribute("uid", uid);
         viewPath = "/jsp/editPwd.jsp";
      }else if(cmd.equals("updatePwd")) {
         String uid = request.getParameter("uid");
         String newPwd = request.getParameter("pwd");
         UserDAO dao = new UserDAO();
         boolean updated = dao.updatePwd(new User(uid, newPwd));
         sendJSON("updated", updated+"");
      }else if(cmd.equals("delete")) {
         String uid = request.getParameter("uid");
         UserDAO dao = new UserDAO();
         boolean deleted = dao.delete(uid);
         sendJSON("deleted", deleted+"");
      }
      return viewPath;
   }
   
   private void sendJSON(String key, String value) {
      String json = String.format("{\"%s\":%s}", key, value);
      PrintWriter out = null;
      try {
         out = response.getWriter();
      } catch (IOException e) {
         e.printStackTrace();
      }
      out.print(json);
      out.flush();
   }
   
   private boolean loginCheck() {
      Object obj = request.getSession().getAttribute("uid");
      return obj!=null;
   }
}
