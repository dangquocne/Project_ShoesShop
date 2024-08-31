package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import model.Product;

/**
 * Servlet implementation class SearchByAjaxController
 */
@WebServlet("/searchAjax")
public class SearchByAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByAjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String txtSearch =request.getParameter("txt");
		
		DAO dao= new DAO();
		List<Product> list = dao.SearchByName(txtSearch);
		PrintWriter out= response.getWriter();
		
		for (Product o : list) {
			out.print(" <div class=\"product col-12 col-md-6 col-lg-4\">\r\n"
					+ "                                <div class=\"card \">\r\n"
					+ "                                    <img class=\"card-img-top\" src=\""+o.getImage()+"\" alt=\"Card image cap\">\r\n"
					+ "                                    <div class=\"card-body \">\r\n"
					+ "                                        <h4 class=\"card-title show_txt\"><a href=\"detail?pid="+o.getId()+"\" title=\"View Product\">"+o.getName()+"</a></h4>\r\n"
					+ "                                        <p class=\"card-text show_txt\">"+o.getTitle()+"\r\n"
					+ "                                        </p>\r\n"
					+ "                                        <div class=\"row\">\r\n"
					+ "                                            <div class=\"col\">\r\n"
					+ "                                                <p class=\"btn btn-danger btn-block\">"+o.getPrice()+" $</p>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"col\">\r\n"
					+ "                                                <a href=\"#\" class=\"btn btn-success btn-block\">Add to cart</a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>");
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
