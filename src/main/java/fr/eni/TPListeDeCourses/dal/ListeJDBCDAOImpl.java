package fr.eni.TPListeDeCourses.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.TPListeDeCourses.BusinessException;
import fr.eni.TPListeDeCourses.bo.Article;
import fr.eni.TPListeDeCourses.bo.Liste;
import fr.eni.TPListeDeCourses.dal.CodesResultatDAL;
import fr.eni.TPListeDeCourses.dal.ConnectionProvider;


public class ListeJDBCDAOImpl implements ListeDAO {

	private static final String SELECT_ALL = "SELECT id, nom FROM LISTES";
	private static final String SELECT_BY_ID =	"select " + 
												"	l.id as id_liste, " + 
												"	l.nom as nom_liste, " + 
												"	a.id as id_article, " + 
												"	a.nom as nom_article, " + 
												"	a.coche " +
												"from " + 
												"	listes l " + 
												"	left join articles a on l.id=a.id_liste "+
												"where l.id=?";
	private static final String INSERT_LISTE = "insert into LISTES(nom) values(?);";
	private static final String INSERT_ARTICLE = "insert into ARTICLES(nom, id_liste) values(?,?);";
	private static final String DELETE_ARTICLE = "delete from ARTICLES where id=?";
	private static final String DELETE_LISTE = "delete from LISTES where id=?";
	private static final String UPDATE_COCHE_ARTICLE="update ARTICLES set coche=1 where id=?";
	private static final String UPDATE_DECOCHE_ARTICLE="update ARTICLES set coche=0 where id=?";
	private static final String UPDATE_DECOCHE_ARTICLES="update ARTICLES set coche=0 where id_liste=?";
	@Override
	
	public void insertArticle(Article article) throws BusinessException {
		if(article==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setInt(2, article.getId_Liste());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				article.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
			
		}	
	}

	

	@Override
	public void deleteArticle(int id) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
		
			PreparedStatement stmt = cnx.prepareStatement(DELETE_ARTICLE, Statement.RETURN_GENERATED_KEYS); 
			stmt.setInt(1, id);
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
	            businessException.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_ECHEC);
		}
	}

	@Override
	public void addListe(Liste liste) throws BusinessException {
		if(liste==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_LISTE_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_LISTE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, liste.getNomListe());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				liste.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.INSERT_LISTE_ECHEC);
			
		}	
	}

	@Override
	public void deleteListe(int id_Liste) {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			
			PreparedStatement stmt = cnx.prepareStatement(DELETE_LISTE, Statement.RETURN_GENERATED_KEYS); 
			stmt.setInt(1, id_Liste);
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
	            businessException.ajouterErreur(CodesResultatDAL.DELETE_LISTE_ECHEC);
		}
	}

	@Override
	public List<Liste> selectall() throws BusinessException {
		List<Liste> liste= new ArrayList<>();
		
	try(Connection cnx = ConnectionProvider.getConnection()) {
		Statement stmt = cnx.createStatement();
		
		try (ResultSet rs = stmt.executeQuery(SELECT_ALL);) {
			if (rs.next()) {
				Liste listeListe = new Liste(rs.getInt("id"), rs.getString("nomListe")); 
				 liste.add(listeListe); 
			}
		}
	}
	
		catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.SELECT_LISTE_ECHEC);
			}
	return liste;
	}
		



	@Override
	public Liste selectbyid(int id_Liste) throws BusinessException {
		Liste liste=null; 
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id_Liste);
			ResultSet rs = pstmt.executeQuery();
			boolean premiereLigne=true;
			while(rs.next())
			{
				if(premiereLigne)
				{
					liste.setId(rs.getInt("id_liste"));
					liste.setNomListe(rs.getString("nom_liste"));
					premiereLigne=false;
				}
				if(rs.getString("nom_article")!=null)
				{
					liste.getArticles().add(new Article(rs.getInt("id_article"), rs.getString("nom_article"), rs.getBoolean("coche")));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
			throw businessException;
		}
		if(liste.getId()==0)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_INEXISTANTE);
			throw businessException;
		}
		
		return liste;

		
	}

			@Override
			public void cocher(int idArticle) throws BusinessException {
				try(Connection cnx = ConnectionProvider.getConnection())
				{
					PreparedStatement pstmt = cnx.prepareStatement(UPDATE_COCHE_ARTICLE);
					pstmt.setInt(1, idArticle);
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.COCHE_ARTICLE_ERREUR);
					throw businessException;
				}
				
			}

			@Override
			public void decocher(int idArticle) throws BusinessException {
				try(Connection cnx = ConnectionProvider.getConnection())
				{
					PreparedStatement pstmt = cnx.prepareStatement(UPDATE_DECOCHE_ARTICLE);
					pstmt.setInt(1, idArticle);
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.DECOCHE_ARTICLE_ERREUR);
					throw businessException;
				}
				
			}

			@Override
			public void reset(int idListeCourse) throws BusinessException {
				try(Connection cnx = ConnectionProvider.getConnection())
				{
					PreparedStatement pstmt = cnx.prepareStatement(UPDATE_DECOCHE_ARTICLES);
					pstmt.setInt(1, idListeCourse);
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.DECOCHE_ARTICLES_ERREUR);
					throw businessException;
				}
				
			}

}
