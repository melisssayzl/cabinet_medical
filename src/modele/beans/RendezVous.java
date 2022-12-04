package modele.beans;

import java.sql.Date;

public class RendezVous {

	private Long id = null;
	private Date datePR = null;
	private Date dateRD = null;
	private String observation = "";
	private Long idMedecin = null;
	private Long idPatient = null;

//	id
//	ID 	datePR
//	Date de prise 	dateRD
//	Date du rendez-vous 	observation
//	Observation 	id_patient
//	ID Patient 	id_medecin
	
	

	public RendezVous() {
		
	}

	public RendezVous(Long id, Date datePR, Date dateRD, String observation, Long idPatient, Long idMedecin) {
	super();
	this.id = id;
	this.datePR = datePR;
	this.dateRD = dateRD;
	this.observation = observation;
	this.idMedecin = idMedecin;
	this.idPatient = idPatient;
}
	
	public void setRendezVousParameters(Long id, Date datePR, Date dateRD, String observation, Long idPatient, Long idMedecin) {
		this.id = id;
		this.datePR = datePR;
		this.dateRD = dateRD;
		this.observation = observation;
		this.idMedecin = idMedecin;
		this.idPatient = idPatient;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	public Date getDatePR() {
		return datePR;
	}

	public void setDatePR(Date datePR) {
		this.datePR = datePR;
	}

	public Date getDateRD() {
		return dateRD;
	}

	public void setDateRD(Date dateRD) {
		this.dateRD = dateRD;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
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
		String str = this.getId()+" "+this.getDatePR()+" "+this.getDateRD() ;
		return str;
	}

	

}
