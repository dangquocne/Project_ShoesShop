package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import model.Account;

/**
 * Servlet implementation class SignupController
 */
@WebServlet("/signup")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		String re_pass= request.getParameter("repass");
		
		//Kiểm tra mật khẩu có trùng vs mật khẩu nhập lại
		if(!password.equals(re_pass)) {
			request.setAttribute("baoLoi", "Mat khau nhap lai khong khop");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
//			response.sendRedirect("Login.jsp");
		}else {
			  DAO dao= new DAO();
			  Account a= dao.checkAccountExits(username);
			  if(a==null) {
				  //dc signup
				  dao.signUp(username, password);
				  response.sendRedirect("home");
			  }else {
				  // day ve trang login.jsp
				  request.setAttribute("baoLoi", "Account name exists");
				  request.getRequestDispatcher("Login.jsp").forward(request, response);
//				  response.sendRedirect("Login.jsp");
			  }
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
