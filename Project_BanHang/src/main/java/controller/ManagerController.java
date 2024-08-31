package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import model.Account;
import model.Category;
import model.Product;

/**
 * Servlet implementation class ManagerController
 */
@WebServlet("/manager")
public class ManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		Account a= (Account) session.getAttribute("acc");
		
		String indexPage= request.getParameter("index");
		//nếu trang kế tiếp bằng null thì quay lạy trang 1
		if(indexPage == null) {
			indexPage="1";
		}
		int index= Integer.parseInt(indexPage);
		
		
				
		int id= a.getId();
		DAO dao= new DAO();
//		List<Product> list= dao.getProductBySellID(id);
		List<Product> list= dao.pagingProduct(id, index);
		List<Category> listC= dao.getAllCategory();
		
		
		//b1: chia trang cho san rphẩm trong managerProducr.jsp
		int count= dao.getTotalProduct();
		int endPage= count/5;
		if(count % 5 != 0) {
			endPage++;
		}
		
		
		request.setAttribute("listP", list);
		request.setAttribute("listC", listC);
		request.setAttribute("endP", endPage);
		request.setAttribute("tag", index);
		
		request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
