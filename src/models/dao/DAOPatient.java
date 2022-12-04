package models.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import modele.beans.Patient;
import utils.DatabaseConfig;

public class DAOPatient {
	private static final String TABLE_NAME = "patient";

	private DAOPatient() {

	}

	// prendre une ligne d ela table de la bdd et construire un objet
	public static Patient getByLine(List<Object> line) {
		Patient patient = new Patient();
		// Le methode prend en parametre uneliste d'objets et retourne un candidat
		patient.setId((Long) line.get(0));
		patient.setNom(line.get(1).toString());
		patient.setPrenom(line.get(2).toString());
		patient.setAdresse(line.get(3).toString());
		patient.setAge((Long) line.get(4));
		patient.setGs(line.get(5) == null ? null : line.get(5).toString());
		patient.setTel(line.get(6).toString());
		patient.setTaille((Long) line.get(7));
		patient.setPoids((Long) line.get(8));

		return patient;

	}

	public static Patient getByID(long id) {
		Patient patient = new Patient();
		// on selelctionne le candidat ayant l'id indiqué de la table candidat
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE id = ?", id);
		if (data.size() == 1) {
			// on retourne les informations du candidat
			patient = getByLine(data.get(0));
		}

		return patient;

	}

	public static List<Patient> getList() {
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE 1");
		List<Patient> list = new ArrayList<Patient>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list;

	}

	public static List<List<Object>> getList2() {
		List<List<Object>> data = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM `" + TABLE_NAME + "` WHERE 1");

		return data;

	}

	public static List<Patient> getList3(String s) {

		List<List<Object>> data = DatabaseConfig.getSingleton()

				.executeQueryPS(
						"SELECT * FROM `" + TABLE_NAME + "` WHERE nom LIKE '%" + s + "%' or prenom LIKE '%" + s + "%'");

		// selection par caractere
		// SELECT * FROM `" + TABLE_NAME + "` WHERE nom LIKE '"+s+"%'

		List<Patient> list = new ArrayList<Patient>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list;
	}

	public static List<Patient> getList4(Patient p) {

		List<List<Object>> data = DatabaseConfig.getSingleton()

				.executeQueryPS(
						"SELECT * FROM `" + TABLE_NAME + "` WHERE nom LIKE '%" + p.getNom() + "%' and prenom LIKE '%" + p.getPrenom() + "%'");
         
		//selection dune chaine de caracteres
		// "SELECT * FROM `" + TABLE_NAME + "` WHERE nom LIKE '%ami%' or prenom LIKE '%ami%'";

		// selection par caractere
		// SELECT * FROM `" + TABLE_NAME + "` WHERE nom LIKE '"+s+"%'

		List<Patient> list = new ArrayList<Patient>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list;

	}
	
	public static List<Patient> getList5(Patient p) {

		List<List<Object>> data = DatabaseConfig.getSingleton()

				.executeQueryPS(
						"SELECT * FROM `" + TABLE_NAME + "` WHERE nom LIKE '%" + p.getNom() + "%' and prenom LIKE '%" + p.getPrenom() + "%'");
         
		//selection dune chaine de caracteres
		// "SELECT * FROM `" + TABLE_NAME + "` WHERE nom LIKE '%ami%' or prenom LIKE '%ami%'";

		// selection par caractere
		// SELECT * FROM `" + TABLE_NAME + "` WHERE nom LIKE '"+s+"%'

		List<Patient> list = new ArrayList<Patient>();

		// parcourir la liste (line : nom prenom ,...)
		for (List<Object> line : data) {
			// le resultat de getbyline est un candidat
			list.add(getByLine(line));
		}

		return list;

	}

// 	id
//ID 	nom
//Nom 	prenom
//Prenom 	adresse
//Adresse 	age
//Age 	gs
//Groupe Sanguin 	tel
//Télephone 	taille
//Taille 	Poids
//Poids 

	public static long insert(Patient patient) {

		DatabaseConfig.getSingleton().executePS(
				"INSERT INTO `" + TABLE_NAME + "`" + "(`id`,`nom`,`prenom`,`adresse`,`age`,`gs`,`tel`,`taille`,`poids`)"
						+ "VALUES" + "( ? ,? ,? ,? ,? ,? ,? ,? ,? )",
				null, patient.getNom(), patient.getPrenom(), patient.getAdresse(), patient.getAge(), patient.getGs(),
				patient.getTel(), patient.getTaille(), patient.getPoids());
		// on cherche mtn le dernier id pour laffecter au candidat

		// Selectionnner la derniere ligne de la table
		List<List<Object>> list = DatabaseConfig.getSingleton().executeQueryPS(

				"SELECT `id` FROM `" + TABLE_NAME + "` WHERE 1 ORDER BY `id` DESC LIMIT 1");

		Object lastID = null;

		// Recuperer l'id du dernier element
		if (list.size() >= 1) {
			lastID = list.get(0).get(0);

		}

		patient.setId((Long) lastID);
		return (long) lastID;

	}

	public static boolean delete(long id) {
		String sql = "DELETE FROM `" + TABLE_NAME + "` WHERE `id`= ?";
		return DatabaseConfig.getSingleton().executePS(sql, id);
	}

	public static boolean delete(Patient patient) {
		return delete(patient.getId());
	}

	public static boolean update(Patient patient) {
		String sql = "UPDATE `" + TABLE_NAME + "`" + "SET" + "`nom` = ?," + "`prenom` = ?," + "`adresse`=?,"
				+ "`age`= ?," + "`gs`= ?," + "`tel`= ?," + "`taille`= ?," + "`poids` = ?" + " WHERE `id` = ?";

		return DatabaseConfig.getSingleton().executePS(sql, patient.getNom(), patient.getPrenom(), patient.getAdresse(),
				patient.getAge(), patient.getGs(), patient.getTel(), patient.getTaille(), patient.getPoids(),
				patient.getId()

		);
	}

}
