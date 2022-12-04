package models.dao;

import java.util.ArrayList;
import java.util.List;

import modele.beans.LigneOrdonnance;
import utils.DatabaseConfig;

public class DAOLigneOrdonnance {
	private static final String TABLE_NAME = "ligne_ordonnance";

	private DAOLigneOrdonnance() {

	}

	//prendre une ligne d ela table de la bdd et construire un objet 
	public static LigneOrdonnance getByLine(List<Object> line) {
		LigneOrdonnance ligneOrdonnance = new LigneOrdonnance();
		// Le methode prend en parametre uneliste d'objets et retourne un candidat
		ligneOrdonnance.setId((Long) line.get(0));
		ligneOrdonnance.setIdConsultation(line.get(1)==null?null :(long)line.get(1));
		ligneOrdonnance.setMedicament(line.get(2).toString());
		ligneOrdonnance.setQuantite((Long)line.get(3));
		ligneOrdonnance.setNbPriseJour((Long) line.get(4));
		ligneOrdonnance.setNbJours((Long)line.get(5));
		ligneOrdonnance.setObservation(line.get(6).toString());
	
		return ligneOrdonnance;

	}



	public static LigneOrdonnance getByID(long id) {
		LigneOrdonnance ligneOrdonnance = new LigneOrdonnance();
		// on selelctionne le candidat ayant l'id indiqué de la table candidat
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE id = ?", id);
		if (data.size() == 1) {
			// on retourne les informations du candidat
			ligneOrdonnance = getByLine(data.get(0));
		}

		return ligneOrdonnance;

	}

	public static List<LigneOrdonnance> getList() {
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE 1");
		List<LigneOrdonnance> list = new ArrayList<LigneOrdonnance>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list;

	}
	
	public static List<LigneOrdonnance> getListByIDconsu(long id) {
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE `id_consultation`="+id+"");
		List<LigneOrdonnance> list = new ArrayList<LigneOrdonnance>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list;

	}


	
// 	id
//ID 	id_consultation
//Id consultaton 	medicament
//Médicament 	quantite
//Quantité 	nb_prise_jour
//Nb prises par jour 	nb_jours
//Nb jours 	observation
//Observation 	
	
	public static long insert(LigneOrdonnance ligneOrdonnance) {

		DatabaseConfig.getSingleton().executePS(
				"INSERT INTO `" + TABLE_NAME + "`"
						+ "(`id`,`id_consultation`,`medicament`,`quantite`,`nb_prise_jour`,`nb_jours`,`observation`)" + "VALUES"
						+ "( ? ,? ,? ,? ,? ,? ,? )",
				null,ligneOrdonnance.getIdConsultation(),ligneOrdonnance.getMedicament(),ligneOrdonnance.getQuantite(),ligneOrdonnance.getNbPriseJour(),ligneOrdonnance.getNbJours(),ligneOrdonnance.getObservation());
		// on cherche mtn le dernier id pour laffecter au candidat

		// Selectionnner la derniere ligne de la table
		List<List<Object>> list = DatabaseConfig.getSingleton().executeQueryPS(

				"SELECT `id` FROM `" + TABLE_NAME + "` WHERE 1 ORDER BY `id` DESC LIMIT 1");

		Object lastID = null;

		// Recuperer l'id du dernier element
		if (list.size() >= 1) {
			lastID = list.get(0).get(0);

		}

		ligneOrdonnance.setId((Long) lastID);
		return (long) lastID;

	}
	
	public static boolean delete(long id) {
		String sql ="DELETE FROM `" + TABLE_NAME + "` WHERE `id`= ?" ;
		return DatabaseConfig.getSingleton().executePS(sql,id);
	}
	public static boolean delete(LigneOrdonnance ligneOrdonnance) {
		return delete(ligneOrdonnance.getId());
	}
	
	public static boolean update (LigneOrdonnance ligneOrdonnance) 
	{
		String sql = "UPDATE `"+TABLE_NAME+"`"
				+ "SET"
				
				+ "`id_consultation`=?,"
				+ "`medicament`=?,"
				+ "`quantite`=?,"
				+ "`nb_prise_jour`=?,"
				+ "`nb_jours`=?,`"
				+ "observation`=?"
				+ "WHERE"
				+ "`id`=?";
				
		
		return DatabaseConfig.getSingleton().executePS(
				sql, 
				ligneOrdonnance.getIdConsultation(),
				ligneOrdonnance.getMedicament(),
				ligneOrdonnance.getQuantite(),
				ligneOrdonnance.getNbPriseJour(),
				ligneOrdonnance.getNbJours(),
				ligneOrdonnance.getObservation(),
				ligneOrdonnance.getId()
				
		) ;
	}
	
}
