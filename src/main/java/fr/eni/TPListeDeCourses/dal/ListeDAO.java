package fr.eni.TPListeDeCourses.dal;

import java.util.List;

import fr.eni.TPListeDeCourses.BusinessException;
import fr.eni.TPListeDeCourses.bo.Article;
import fr.eni.TPListeDeCourses.bo.Liste;

public interface ListeDAO {

	void insertArticle(Article article) throws BusinessException; 
	void deleteArticle(int id) throws BusinessException; 
	void addListe(Liste liste) throws BusinessException; 
	void deleteListe(int id_Liste) throws BusinessException; 
	List<Liste>  selectall() throws BusinessException; 
	Liste selectbyid(int id_Liste) throws BusinessException; 
	void cocher(int id) throws BusinessException; 
	void decocher(int id) throws BusinessException; 
	void reset(int id_Liste) throws BusinessException; 
}
