package com.janew.login.sevlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.janew.login.dao.LoginRegister;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String user = request.getParameter("user");
        String pass = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        
        if(LoginRegister.checkUser(user, pass))
        {
        	out.println("Username has been used");
            RequestDispatcher rs = request.getRequestDispatcher("register.html");
            rs.include(request, response);
        }
        else if(pass != pass2){
        	out.println("Password not match");
            RequestDispatcher rs = request.getRequestDispatcher("register.html");
            rs.include(request, response);
        }
        else if (LoginRegister.fillin(user, pass)) {
	    	out.println("Register Successfully!");
	        RequestDispatcher rs = request.getRequestDispatcher("index.html");
	        rs.include(request, response);
        } else {
			out.println("Register Failed!");
        }
	}

}
