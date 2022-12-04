package modele.beans;

import java.sql.Date;

public class Consultation {

	private Long id = null;
	private String numConsultation = "";
	private Long idPatient = null;
	private Date date = null;
	private Long idMedecin = null;


// 	id
//ID 	num_consultation
//Num de consultation 	id_patient
//ID Patient 	date
//Date 	id_medecin 	
//	

	public Consultation() {
		
	}


	
	public Consultation(Long id, String numConsultation, Long idPatient, Date date, Long idMedecin) {
	super();
	this.id = id;
	this.numConsultation = numConsultation;
	this.idPatient = idPatient;
	this.date = date;
	this.idMedecin = idMedecin;
}



	public void setConsultationParameters(Long id, String numConsultation, Long idPatient, Date date, Long idMedecin) {
		this.id = id;
		this.date = date;
		this.numConsultation = numConsultation;
		this.idMedecin = idMedecin;
		this.idPatient = idPatient;
		
	}

	public String getNumConsultation() {
		return numConsultation;
	}



	public void setNumConsultation(String numConsultation) {
		this.numConsultation = numConsultation;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
  
	

	
	public Long getIdMedecin() {
		return idMedecin;
	}

	public void setIdMedecin(Long idMedecin) {
		this.idMedecin = idMedecin;
	}

	public Long getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Long idPatient) {
		this.idPatient = idPatient;
	}

	@Override
	public String toString() {
		String str = this.getId().toString() ;
		return str;
	}



	

	

}
