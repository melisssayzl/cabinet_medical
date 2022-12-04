package models.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import modele.beans.Consultation;
import utils.DatabaseConfig;

public class DAOConsultation {
	private static final String TABLE_NAME = "consultation";

	private  DAOConsultation() {
		// TODO Auto-generated constructor stub
	}

	//prendre une ligne d ela table de la bdd et construire un objet 
	public static Consultation getByLine(List<Object> line) {
		Consultation consultation = new Consultation();
		// Le methode prend en parametre uneliste d'objets et retourne un candidat
		consultation.setId((Long) line.get(0));
		consultation.setDate((Date)line.get(3));
		
		consultation.setNumConsultation( line.get(1).toString());
		consultation.setIdPatient(line.get(2)==null?null :(long)line.get(2));
		consultation.setIdMedecin(line.get(4)==null?null :(long)line.get(4));
		
		return consultation;

	}



	public static Consultation getByID(long id) {
		Consultation consultation = new Consultation();
		// on selelctionne le candidat ayant l'id indiqué de la table candidat
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE id = ?", id);
		if (data.size() == 1) {
			// on retourne les informations du candidat
			consultation = getByLine(data.get(0));
		}

		return consultation;

	}

	public static List<Consultation> getList() {
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE 1");
		List<Consultation> list = new ArrayList<Consultation>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list;

	}
	
	public static List<Consultation> getListByID(Long id) {
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE `id_patient`="+id+"");
		List<Consultation> list = new ArrayList<Consultation>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list;

	}



	public static long insert(Consultation consultation) {

		DatabaseConfig.getSingleton().executePS(
				"INSERT INTO `" + TABLE_NAME + "`"
						+ "(`id`,`num_consultation`,`id_patient`,`date`,`id_medecin`)" + "VALUES"
						+ "( ? ,? ,? ,? ,?)",
				null, consultation.getNumConsultation(),consultation.getIdPatient(),consultation.getDate() ,consultation.getIdMedecin());
		// on cherche mtn le dernier id pour laffecter au candidat

		// Selectionnner la derniere ligne de la table
		List<List<Object>> list = DatabaseConfig.getSingleton().executeQueryPS(

				"SELECT `id` FROM `" + TABLE_NAME + "` WHERE 1 ORDER BY `id` DESC LIMIT 1");

		Object lastID = null;

		// Recuperer l'id du dernier element
		if (list.size() >= 1) {
			lastID = list.get(0).get(0);

		}

		consultation.setId((Long) lastID);
		return (long) lastID;

	}
	
	public static boolean delete(long id) {
		String sql ="DELETE FROM `" + TABLE_NAME + "` WHERE `id`= ?" ;
		return DatabaseConfig.getSingleton().executePS(sql,id);
	}
	
	public static boolean deleteIDPatient(long idPatient) {
		String sql ="DELETE FROM `" + TABLE_NAME + "` WHERE `id_patient`= ?" ;
		return DatabaseConfig.getSingleton().executePS(sql,idPatient);
	}
	public static boolean delete(Consultation consultation) {
		return delete(consultation.getId());
	}

	
	
	public static boolean update (Consultation consultation) 
	{
		String sql = "UPDATE `"+TABLE_NAME+"`"
				+ "SET"
				+ "`num_consultation` = ?,  "
				+ "`id_patient` = ?,"
				+ "`date`= ? ,"
				+ "`id_medecin`= ? "
				+ " WHERE `id` = ?";
		
		return DatabaseConfig.getSingleton().executePS(
				sql, 
				consultation.getNumConsultation(), 
				consultation.getIdPatient(), 
				consultation.getDate(),  
				consultation.getIdMedecin(), 
				consultation.getId()
				
		) ;
	}
	
}
