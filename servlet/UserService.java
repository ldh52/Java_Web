package com.test.sku.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserService
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public UserService() {}
	
	public UserService(HttpServletRequest request, HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
	}

	public String Process()
	{
		String cmd = request.getParameter("cmd");
		String viewPath = null;
		
		if(cmd==null || cmd.equals("loginForm")) {
			viewPath = "/jsp/loginForm.jsp";
		} else if(cmd.equals("login")) {
			// 로그인 절차 수행
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");

			User user = new User(uid,pwd);
			UserDAO dao = new UserDAO();
			boolean ok = dao.login(user);

			request.setAttribute("login", true);
			viewPath = "/jsp/loginResult.jsp";
		} 
		
		else if (cmd.equals("list")) {
            UserDAO dao = new UserDAO();
            List<User> userList = dao.getList();
            request.setAttribute("list", userList);
            viewPath = "/jsp/userList.jsp";
        } 
		
		else if (cmd.equals("detail")) {
            UserDAO dao = new UserDAO();
            String uid = request.getParameter("uid");
            User userDetail = dao.getUserDetailList(uid);
            request.setAttribute("detail", userDetail);
            viewPath = "/jsp/userDetailList.jsp";
        }
				
		else if(cmd.equals("edit")) {
	         String uid = request.getParameter("uid");
	         request.setAttribute("uid", uid);
	         viewPath = "/jsp/editPwd.jsp";
	    } else if(cmd.equals("updatePwd")) {
	         String uid = request.getParameter("uid");
	         String newPwd = request.getParameter("pwd");
	         UserDAO dao = new UserDAO();
	         boolean updated = dao.updatePwd(new User(uid, newPwd));
	         sendJSON("updated", updated+"");
	    }

        else if (cmd.equals("delete")) {
	         String uid = request.getParameter("uid");
	         UserDAO dao = new UserDAO();
	         boolean deleted = dao.deleteUser(uid);
	         
	         sendJSON("deleted", deleted+"");
	    }
		
        else if (cmd.equals("addForm")) { 
        	viewPath = "/jsp/userinput.jsp";
        } 
        else if(cmd.equals("add")) 
        {
        	System.out.println("추가 시작");
        	 String uid = request.getParameter("uid");
             String pwd = request.getParameter("pwd");
             User user = new User(uid, pwd);
             UserDAO dao = new UserDAO();
             boolean added = dao.add(user);
             sendJSON("added", added+"");
        }
		
		return viewPath;
	}
	
	private void sendJSON(String key, String value) {
		String json = String.format("{\"%s\":%s}", key, value);
		PrintWriter out = null;
		try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
        out.print(json);
        out.flush();
	}
}