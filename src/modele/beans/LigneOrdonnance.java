package modele.beans;

public class LigneOrdonnance {

	
	private Long id = null;
	private Long idConsultation = null;
	private String medicament = "";
	private Long quantite = null;
	private Long nbPriseJour = null;
	private Long nbJours = null;
	private String observation = "";

	public LigneOrdonnance(Long id, Long idConsultation, String medicament, Long quantite, Long nbPriseJour,
			Long nbJours, String observation) {
		super();
		this.id = id;
		this.idConsultation = idConsultation;
		this.medicament = medicament;
		this.quantite = quantite;
		this.nbPriseJour = nbPriseJour;
		this.nbJours = nbJours;
		this.observation = observation;
	}

	public LigneOrdonnance() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdConsultation() {
		return idConsultation;
	}

	public void setIdConsultation(Long idConsultation) {
		this.idConsultation = idConsultation;
	}

	public String getMedicament() {
		return medicament;
	}

	public void setMedicament(String medicament) {
		this.medicament = medicament;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Long getNbPriseJour() {
		return nbPriseJour;
	}

	public void setNbPriseJour(Long nbPriseJour) {
		this.nbPriseJour = nbPriseJour;
	}

	public Long getNbJours() {
		return nbJours;
	}

	public void setNbJours(Long nbJours) {
		this.nbJours = nbJours;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public String toString() {
		String str = this.getId()+"";
		return str;
	}

}
