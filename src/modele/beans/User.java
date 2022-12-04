package modele.beans;

public class User {

	
 	
	
	private Long id = null;
	private String nom = "";
	private String prenom = "";
	private String username = "";
	private String password = "";
	private String tel="";
	private String adresse="";
	private String type="infermiere";
	
    
	
	public User(Long id, String nom, String prenom, String username, String password, String tel, String adresse,
			String type) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.tel = tel;
		this.adresse = adresse;
		this.type = type;
	}

	public User() {
		
	}

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

	

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		String str = this.getId()+"( "+this.getNom()+" - "+this.getPrenom()+ " )";
		return str;
	}



}
