package main;

import UI.UIHome;
import UI.UILogin;
import utils.DBConstants;
import utils.DatabaseConfig;

public class Main {
	public static void main(String[] args) { //throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
	
			//UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		

		DatabaseConfig.getSingleton(DBConstants.HOST, DBConstants.PORT, DBConstants.DB_NAME, DBConstants.DB_USER,
				DBConstants.DB_PASSWORD);
//		DAOUser.delete(3);
//
//		userUTILS.checkuserpassword("meli_milo", "PASSWORD123");
//		userUTILS.checkuserpassword("meli_milo", "PASSWORD1234");

//		System.out.println(userUTILS.checkuserpassword1("meli_milo","PASSWORD123"));
//		System.out.println();
//	    userUTILS.checkuserpassword1("meli_milo","PASSWORD1234");

////		@SuppressWarnings("deprecation")
//		RendezVous rendezVous = new RendezVous(null, new java.sql.Date(2923, 01, 10), new java.sql.Date(2023, 02, 10),
//				"sain", null, null);
//		DAORendezVous.insert(rendezVous);
//		rendezVous.setObservation("azuul");
//		DAORendezVous.update(rendezVous);
		
//		Patient patient= new Patient(null, "azul","azppul", "azul",077774644l, "B+","azul",100l,111l);
//		DAOPatient.insert(patient);
//        patient.setGs("A+");
//        DAOPatient.update(patient);
//		DAOPatient.delete(1);
		
//		LigneOrdonnance ligneOrdonnance =  new LigneOrdonnance(null, null,"spasfooon",2l,3l,7l,"Malade");
//		DAOLigneOrdonnance.insert(ligneOrdonnance);
//		DAOLigneOrdonnance.delete(2);
//		ligneOrdonnance.setObservation("azul");
//		DAOLigneOrdonnance.update(ligneOrdonnance);

		
		
//		Consultation consultation = new Consultation(null,"123",null,new java.sql.Date(2420, 12, 5), null);
//		DAOConsultation.insert(consultation);
//		consultation.setNumConsultation("554");
//		DAOConsultation.update(consultation);
//		DAOConsultation.delete(consultation);
//		DAOConsultation.delete(1);
  
		
		UIHome.getSinglton().setVisible(true);
        

	}

}
