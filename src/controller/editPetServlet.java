package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListPet;

/**
 * Servlet implementation class editPetServlet
 */
@WebServlet("/editPetServlet")
public class editPetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editPetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListItemHelper lih = new ListItemHelper();
		
		String breed = request.getParameter("breed");
		String name = request.getParameter("name");
		String owner = request.getParameter("owner");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		ListPet petToUpdate = lih.searchForPetById(tempId);
		petToUpdate.setType(breed);
		petToUpdate.setName(name);
		petToUpdate.setOwner(owner);
		
		lih.updatePet(petToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllPetsServlet").forward(request, response);
		doGet(request, response);
	}

}
