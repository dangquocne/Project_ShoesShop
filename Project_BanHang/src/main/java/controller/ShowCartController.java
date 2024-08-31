package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.DAO;

import model.Product;

/**
 * Servlet implementation class ShowCartController
 */
@WebServlet("/print")
public class ShowCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
		Cookie[] arr = request.getCookies();
        PrintWriter out = response.getWriter();
        List<Product> list = new ArrayList<>();

        DAO dao = new DAO();
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
//                String txt[] = o.getValue().split(",");
            	 String[] txt = URLDecoder.decode(o.getValue(), StandardCharsets.UTF_8.name()).split(",");
                for (String s : txt) {
                	 Product product = dao.getProductByID(s);
                     if (product != null) {
                         list.add(product);
                     }
                }
            }
        }
        
        for (int i = 0; i < list.size(); i++) {
            int count = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getId() == list.get(j).getId()) {
                    count++;
                    list.remove(j);
                    j--;
                    list.get(i).setAmount(count);
                }
            }
        }
        
        double total = 0;
        for (Product o : list) {
            total = total + o.getAmount()* o.getPrice();
        }
        
       
        request.setAttribute("listP", list);
        request.setAttribute("total", total);
        request.setAttribute("vat", 0.1 * total);
        request.setAttribute("sum", 1.1 * total);
        
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
