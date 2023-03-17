package fr.eni.TPListeDeCourses.bll;

import java.util.List;

import fr.eni.TPListeDeCourses.BusinessException;
import fr.eni.TPListeDeCourses.bo.Article;
import fr.eni.TPListeDeCourses.bo.Liste;
import fr.eni.TPListeDeCourses.dal.DAOFactory;
import fr.eni.TPListeDeCourses.dal.ListeDAO;

public class ListeManager {
	private ListeDAO listeCourseDAO;
	
	public ListeManager() {
		this.listeCourseDAO=DAOFactory.getListeDAO();
	}
	
	public List<Liste> selectionnerListes() throws BusinessException
	{
		return this.listeCourseDAO.selectall();
	}

	public Liste selectionnerListe(int idListeCourse) throws BusinessException {
		return this.listeCourseDAO.selectbyid(idListeCourse);
	}

	public void ajouterArticle(int idListeCourse, String nomArticle) throws BusinessException{
		BusinessException businessException = new BusinessException();
		this.validerNomArticle(nomArticle, businessException);
				
		if(!businessException.hasErreurs())
		{
			Liste listeCourse = new Liste();
			listeCourse.setId(idListeCourse);
			
			Article article = new Article(nomArticle.trim());
			listeCourse.getArticles().add(article);
			this.listeCourseDAO.addListe(listeCourse);
		}
		else
		{
			throw businessException;
		}
	}
	
	public Liste ajouterListeEtArticle(String nomListe, String nomArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		this.validerNomListe(nomListe, businessException);
		this.validerNomArticle(nomArticle, businessException);
		
		Liste listeCourse= new Liste();
		
		if(!businessException.hasErreurs())
		{
			listeCourse = new Liste();
			listeCourse.setNomListe(nomListe);
		
			Article article = new Article(nomArticle.trim());
			listeCourse.getArticles().add(article);
			this.listeCourseDAO.addListe(listeCourse);
		}
		else
		{
			throw businessException;
		}
		return listeCourse;
	}

	public void supprimerArticle(int idArticle) throws BusinessException{
		this.listeCourseDAO.deleteArticle(idArticle);
	}
	public void supprimerListeCourse(int idListeCourse) throws BusinessException {
		this.listeCourseDAO.deleteListe(idListeCourse);
	}
	public void cocherArticle(int idArticle) throws BusinessException
	{
		this.listeCourseDAO.cocher(idArticle);
	}
	public void decocherArticle(int idArticle) throws BusinessException
	{
		this.listeCourseDAO.decocher(idArticle);
	}
	public void decocherListe(int idListeCourse) throws BusinessException
	{
		this.listeCourseDAO.decocher(idListeCourse);
	}
	
	private void validerNomListe(String nomListe, BusinessException businessException) {
		if(nomListe==null || nomListe.trim().length()>50)
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_LISTE_NOM_ERREUR);
		}
	}

	private void validerNomArticle(String nomArticle, BusinessException businessException) {
		if(nomArticle==null || nomArticle.trim().length()>50)
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_NOM_ERREUR);
		}
	}

	

	
}

