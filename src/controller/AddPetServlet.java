package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListPet;

/**
 * Servlet implementation class AddPetServlet
 */
@WebServlet("/AddPetServlet")
public class AddPetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String breed = request.getParameter("breed");
		String name = request.getParameter("name");
		String owner = request.getParameter("owner");
		
		ListPet li = new ListPet(breed, name, owner);
		ListItemHelper lih = new ListItemHelper();
		lih.insertPet(li);
		
		getServletContext().getRequestDispatcher("/index.html").forward(request,  response);
	}

}
