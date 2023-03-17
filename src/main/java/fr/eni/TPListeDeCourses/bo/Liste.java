package fr.eni.TPListeDeCourses.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Liste implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; 
	private String nomListe;
	private List<Article> articles =new ArrayList<Article>();
	
	public Liste() {
		super();
	}


	public Liste(int id, String nomListe) {
		super();
		this.id = id;
		this.nomListe = nomListe;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomListe() {
		return nomListe;
	}


	public void setNomListe(String nomListe) {
		this.nomListe = nomListe;
	}


	@Override
	public String toString() {
		return "Liste [id=" + id + ", nomListe=" + nomListe + "]";
	}


	public List<Article> getArticles() {
		return articles;
	}


	public void setArticles(List<Article> articles) {
		this.articles = articles;
	} 
	
	
	
}
