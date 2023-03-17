package fr.eni.TPListeDeCourses.dal;

public abstract class DAOFactory {
	
	public static ListeDAO getListeDAO()
	{
		return new ListeJDBCDAOImpl();
	}
	
	
}
