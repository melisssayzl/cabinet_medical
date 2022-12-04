package modele.beans;

public class Patient {

	private Long id = null;
	private String nom = "";
	private String prenom = "";
	private String adresse = "";
	private Long age = null;
	private String gs = "A+";
	private String tel = "";
	private Long taille = null;
	private Long poids = null;


	

	public Patient() {
		
	}

	public Patient(Long id, String nom, String prenom, String adresse, Long age, String gs, String tel, Long taille,
		Long poids) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.adresse = adresse;
	this.age = age;
	this.gs = gs;
	this.tel = tel;
	this.taille = taille;
	this.poids = poids;
    }
	
//	private void setDatabaseParameters(String host, int port, String dbName, String dbUser, String dbPassword) {
//	this.host = host;
//	this.port = port;
//	this.dbName = dbName;
//	this.dbUser = dbUser;
//	this.dbPassword = dbPassword;
	
	
	public void setPatientParameters(Long id, String nom, String prenom, String adresse, Long age, String gs, String tel, Long taille,Long poids) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.age = age;
		this.gs = gs;
		this.tel = tel;
		this.taille = taille;
		this.poids = poids;
		
	}
//}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getGs() {
		return gs;
	}

	public void setGs(String gs) {
		this.gs = gs;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getTaille() {
		return taille;
	}

	public void setTaille(Long taille) {
		this.taille = taille;
	}

	public Long getPoids() {
		return poids;
	}

	public void setPoids(Long poids) {
		this.poids = poids;
	}

	@Override
	public String toString() {
		String str =this.getNom()+" "+this.getPrenom();
		return str;
	}

}
