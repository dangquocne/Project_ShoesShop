package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import model.Product;

/**
 * Servlet implementation class ListProductController
 */
@WebServlet("/listProduct")
public class ListProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexPage= request.getParameter("index");
		//nếu trang kế tiếp bằng null thì quay lạy trang 1
		if(indexPage == null) {
			indexPage="1";
		}
		
		int index= Integer.parseInt(indexPage);
		
		DAO dao= new DAO();
		//b1: get total product 	
		int count= dao.getTotalProduct();
		int endPage= count/5;
		if(count % 5 != 0) {
			endPage++;
		}
		
////		List<Product> list= dao.pagingProduct(index);
//		
//		request.setAttribute("listP", list);
//		request.setAttribute("endP", endPage);
//		request.setAttribute("tag", index);
//		request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
