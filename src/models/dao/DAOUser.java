package models.dao;

import java.util.ArrayList;
import java.util.List;

import modele.beans.User;
import utils.DatabaseConfig;

public class DAOUser {
	private static final String TABLE_NAME = "user";

	private DAOUser() {

	}
   

	//prendre une ligne d ela table de la bdd et construire un objet 
	public static User getByLine(List<Object> line) {
		User user = new User();
		// Le methode prend en parametre uneliste d'objets et retourne un candidat
		user.setId((Long) line.get(0));
		user.setNom(line.get(1).toString());
		user.setPrenom(line.get(2).toString());
		user.setUsername( line.get(3).toString());
		user.setPassword(line.get(4).toString());
		user.setTel(line.get(5).toString());
		user.setAdresse(line.get(6).toString());
		user.setType(line.get(7) == null ? null : line.get(7).toString());
		
	
		return user;

	}



	public static User getByID(long id) {
		User user = new User();
		// on selelctionne le candidat ayant l'id indiqué de la table candidat
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE id = ?", id);
		if (data.size() == 1) {
			// on retourne les informations du candidat
			user = getByLine(data.get(0));
		}

		return user;

	}

	public static List<User> getList() {
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE 1");
		List<User> list = new ArrayList<User>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list;

	}
	
	public static User getMedecin() {
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE `type`= ? LIMIT 1","medecin");
		List<User> list = new ArrayList<User>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list.get(0);

	}


	public static long insert(User user) {

		
       
		DatabaseConfig.getSingleton().executePS(
				"INSERT INTO `" + TABLE_NAME + "`"
						+ "(`id`,`nom`,`prenom`,`username`,`password`,`tel`,`adresse`,`type`)" + "VALUES"
						+ "( ? ,? ,? ,? ,? ,? ,? ,?)",
				null, user.getNom(), user.getPrenom(), user.getUsername(),
				user.getPassword(),user.getTel(),user.getAdresse(),user.getType());
		// on cherche mtn le dernier id pour laffecter au candidat

		// Selectionnner la derniere ligne de la table
		List<List<Object>> list = DatabaseConfig.getSingleton().executeQueryPS(

				"SELECT `id` FROM `" + TABLE_NAME + "` WHERE 1 ORDER BY `id` DESC LIMIT 1");

		Object lastID = null;

		// Recuperer l'id du dernier element
		if (list.size() >= 1) {
			lastID = list.get(0).get(0);

		}

		user.setId((Long) lastID);
		return (long) lastID;

	}
	
	public static boolean delete(long id) {
		String sql ="DELETE FROM `" + TABLE_NAME + "` WHERE `id`= ?" ;
		return DatabaseConfig.getSingleton().executePS(sql,id);
	}
	public static boolean delete(User user) {
		return delete(user.getId());
	}
	
	public static boolean update (User user) 
	{

		
		String sql ="UPDATE `user` "
				+ "SET"
				+ " `nom` = ?,"
				+ " `prenom` = ?, "
				+ "`username` = ?, "
				+ "`password` = ?,"
				+ "`tel` = ?,"
				+ " `adresse` = ?, "
				+ "`type` = ? "
				+ " WHERE "
				+ "`user`.`id` = ?"; 
		
		
		return DatabaseConfig.getSingleton().executePS(sql,user.getNom(),user.getPrenom(),user.getUsername(),user.getPassword(),
				user.getTel(),user.getAdresse(),user.getType(),user.getId()) ;
	}
	
}
