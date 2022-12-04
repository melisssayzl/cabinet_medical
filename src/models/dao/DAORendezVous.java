package models.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import modele.beans.RendezVous;
import utils.DatabaseConfig;

public class DAORendezVous {
	private static final String TABLE_NAME = "rendez_vous";

	private DAORendezVous() {

	}
   

	
	//prendre une ligne d ela table de la bdd et construire un objet 
	public static RendezVous getByLine(List<Object> line) {
		RendezVous rendezvous = new RendezVous();
		// Le methode prend en parametre uneliste d'objets et retourne un candidat
		rendezvous.setId((Long) line.get(0));
		rendezvous.setDatePR((Date)line.get(1));
		rendezvous.setDateRD((Date)line.get(2));
		rendezvous.setObservation( line.get(3).toString());
		rendezvous.setIdPatient(line.get(4)==null?null :(long)line.get(4));
		rendezvous.setIdMedecin(line.get(5)==null?null :(long)line.get(5));
		
		return rendezvous;

	}



	public static RendezVous getByID(long id) {
		RendezVous rendezvous = new RendezVous();
		// on selelctionne le candidat ayant l'id indiqué de la table candidat
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE id = ?", id);
		if (data.size() == 1) {
			// on retourne les informations du candidat
			rendezvous = getByLine(data.get(0));
		}

		return rendezvous;

	}

	public static List<RendezVous> getList() {
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE 1");
		List<RendezVous> list = new ArrayList<RendezVous>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list;

	}
	
	public static List<RendezVous> getList(Long idPatient) {
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE `id_patient`="+idPatient+"");
		List<RendezVous> list = new ArrayList<RendezVous>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list;

	}


	public static long insert(RendezVous rendezvous) {

		DatabaseConfig.getSingleton().executePS(
				"INSERT INTO `" + TABLE_NAME + "`"
						+ "(`id`,`datePR`,`dateRD`,`observation`,`id_patient`,`id_medecin`)" + "VALUES"
						+ "( ? ,? ,? ,? ,? ,?)",
				null, rendezvous.getDatePR(), rendezvous.getDateRD(), rendezvous.getObservation(),
				rendezvous.getIdPatient(),rendezvous.getIdMedecin());
		// on cherche mtn le dernier id pour laffecter au candidat

		// Selectionnner la derniere ligne de la table
		List<List<Object>> list = DatabaseConfig.getSingleton().executeQueryPS(

				"SELECT `id` FROM `" + TABLE_NAME + "` WHERE 1 ORDER BY `id` DESC LIMIT 1");

		Object lastID = null;

		// Recuperer l'id du dernier element
		if (list.size() >= 1) {
			lastID = list.get(0).get(0);

		}

		rendezvous.setId((Long) lastID);
		return (long) lastID;

	}
	
	public static boolean delete(long id) {
		String sql ="DELETE FROM `" + TABLE_NAME + "` WHERE `id`= ?" ;
		return DatabaseConfig.getSingleton().executePS(sql,id);
	}
	
	public static boolean deleteIDpatient(long idPatient) {
		String sql ="DELETE FROM `" + TABLE_NAME + "` WHERE `id_patient`= ?" ;
		return DatabaseConfig.getSingleton().executePS(sql,idPatient);
	}
	public static boolean delete(RendezVous rendezvous) {
		return delete(rendezvous.getId());
	}
//	id
//	ID 	datePR
//	Date de prise 	dateRD
//	Date du rendez-vous 	observation
//	Observation 	id_patient
//	ID Patient 	id_medecin
	
	public static boolean update (RendezVous rendezvous) 
	{
		String sql = "UPDATE `"+TABLE_NAME+"`"
				+ "SET"
				+ "`datePR` = ?,"
				+ "`dateRD` = ?,  "
				+ "`observation` = ?,  "
				+ "`id_patient` = ?, "
				+ "`id_medecin`= ? "
				+ " WHERE `id` = ?";
		
		return DatabaseConfig.getSingleton().executePS(
				sql, 
				rendezvous.getDatePR(), 
				rendezvous.getDateRD(), 
				rendezvous.getObservation(),  
				rendezvous.getIdPatient(), 
				rendezvous.getIdMedecin(), 
				rendezvous.getId()
				
		) ;
	}
	
}
