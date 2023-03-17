package fr.eni.TPListeDeCourses.bo;

import java.io.Serializable;

public class Article implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; 
	private String nomArticle; 
	private int id_Liste;
	
	
	
	public Article() {
		super();
	}



	public Article(int id, String nomArticle) {
		super();
		this.id = id;
		this.id_Liste = id_Liste;
	}



	

	private boolean coche;
	public boolean isCoche() {
			return coche;
		}
		public void setCoche(boolean coche) {
			this.coche = coche;
		}
	public Article(int id, String nom, boolean coche) {
			this(id,nom);
			this.coche = coche;
		}

	public Article(String trim) {
		this.nomArticle=nomArticle; 
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNomArticle() {
		return nomArticle;
	}



	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}



	public int getId_Liste() {
		return id_Liste;
	}



	public void setId_Liste(int id_Liste) {
		this.id_Liste = id_Liste;
	}



	@Override
	public String toString() {
		return "Article [id=" + id + ", nomArticle=" + nomArticle + ", id_Liste=" + id_Liste + "]";
	} 
	
	
	
}
