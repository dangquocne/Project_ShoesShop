package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
	        String id = request.getParameter("id");
	        Cookie[] arr = request.getCookies();
	        String txt = "";
	        for (Cookie o : arr) {
	            if (o.getName().equals("id")) {
//	                txt = txt + o.getValue();
	            	txt = txt+ URLDecoder.decode(o.getValue(), StandardCharsets.UTF_8.name());
	                o.setMaxAge(0);
	                response.addCookie(o);
	            }
	        }
	        if (txt.isEmpty()) {
	            txt = id;
	        } else {
	            txt = txt + ", " + id;
	        }
	        
	        String encodedTxt = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
	        Cookie c = new Cookie("id", encodedTxt);
	        c.setMaxAge(60 * 60 * 24);
	        response.addCookie(c);
	        
	        response.sendRedirect("print");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
