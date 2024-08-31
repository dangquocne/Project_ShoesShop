package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import model.Account;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		
		DAO dao= new DAO();
		Account a= dao.login(username, password);
		
		//kiem tra xem tai khoan co ton tai khong 
		if(a==null) {
			request.setAttribute("mess", "Wrong user or pass");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}else {
			
			//Khi dung cau lẹnh này thì khi cần đẩy dữ liệu lên ms dùng
//			request.getRequestDispatcher("home").forward(request, response);
			
		    //sử dụng để lưu trữ account trên session
			HttpSession session = request.getSession();
			session.setAttribute("acc", a);	
			//còn khi k cần đẩy dữ liệu thì dùng cái này
			response.sendRedirect("home");
		  
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
