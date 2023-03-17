package fr.eni.TPListeDeCourses.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.TPListeDeCourses.BusinessException;
import fr.eni.TPListeDeCourses.bll.ListeManager;

/**
 * Servlet implementation class ServletListe
 */
@WebServlet("/ServletListe")
public class ServletListe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListeManager liste = new ListeManager(); 
		int id_Liste=0; 
		List<Integer>listeCodesErreur = new ArrayList<>();
		if(request.getParameter("supprimer") != null) {
			id_Liste = this.lireParametreSupprimer(request, listeCodesErreur);
			}
				if(listeCodesErreur.size()>0)
				{
				request.setAttribute("listeCodesErreur",listeCodesErreur);
				}
				else
				{
					try {
						liste.supprimerListeCourse(id_Liste);
				
						}catch(BusinessException e) {
				
						request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
						e.printStackTrace();
						}
		
					try {
					request.setAttribute("selectionnerListe", liste.selectionnerListes()); 
					} catch(BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Listes.jsp"); 
		rd.forward(request, response);
		
	}
	}

	private int lireParametreSupprimer(HttpServletRequest request, List<Integer> listeCodesErreur) {
		int idListeCourse=0;
		try
		{
			if(request.getParameter("supprimer")!=null)
			{
				idListeCourse = Integer.parseInt(request.getParameter("supprimer"));
			}
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ID_LISTE_ERREUR);
		}
		return idListeCourse;
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
