package constants;

import javax.swing.ImageIcon;

public final class IconConstants {
	private IconConstants() {
	}
	
	public static final ImageIcon ENTER_ICON = new ImageIcon( IconConstants.class.getResource("/resources/icons/enter.png") );
	public static final ImageIcon ENTER_ICON_30_30 = ImageUtils.resizeIconTo( ENTER_ICON , 30, 30);
	
	public static final ImageIcon EXIT_ICON = new ImageIcon( IconConstants.class.getResource("/resources/icons/exit.png") );
	public static final ImageIcon EXIT_ICON_30_30 = ImageUtils.resizeIconTo( EXIT_ICON , 300,400);
	
	
	public static final ImageIcon GP_ICON = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-patient-64(2).png") );
	public static final ImageIcon GP_ICON_50_50 = ImageUtils.resizeIconTo( GP_ICON, 50, 50);
	
	public static final ImageIcon GR_ICON = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-planificateur-64(1).png") );
	public static final ImageIcon GR_ICON_50_50 = ImageUtils.resizeIconTo( GR_ICON, 50, 50);
	
	public static final ImageIcon DP_ICON = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-plan-de-traitement-100(1).png") );
	public static final ImageIcon DP_ICON_50_50 = ImageUtils.resizeIconTo( DP_ICON, 50, 50);
	
	public static final ImageIcon NOTES_ICON = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-notes-64.png") );
	public static final ImageIcon NOTES_ICON_50_50 = ImageUtils.resizeIconTo( NOTES_ICON, 50, 50);
	
	public static final ImageIcon SETTINGS_ICON = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-paramètres-64.png") );
	public static final ImageIcon SETTINGS_ICON_50_50 = ImageUtils.resizeIconTo( SETTINGS_ICON, 50, 50);
	
	public static final ImageIcon ABOUT_ICON = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-point-d'interrogation-100.png") );
	public static final ImageIcon ABOUT_ICON_50_50 = ImageUtils.resizeIconTo(ABOUT_ICON, 50, 50);
	
	public static final ImageIcon HOME_ICON = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-accueil-100(1).png") );
	public static final ImageIcon HOME_ICON_50_50 = ImageUtils.resizeIconTo( HOME_ICON, 50, 50);
	
	public static final ImageIcon DECONNEX_ICON = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-déconnexion-64(1).png") );
	public static final ImageIcon DECONNEX_ICON_50_50 = ImageUtils.resizeIconTo( DECONNEX_ICON, 50, 50);
	
	public static final ImageIcon SIDE = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-menu-64.png") );
	public static final ImageIcon SIDE_50_50 = ImageUtils.resizeIconTo( SIDE, 63,63);
	
	
	
	public static final ImageIcon EXIT = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-effacer-64.png") );
	public static final ImageIcon EXIT_ICON_50_50 = ImageUtils.resizeIconTo( EXIT, 52, 52);
	
	public static final ImageIcon MOTIV = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-médecin-64.png") );
	public static final ImageIcon MOTIV_ICON_50_50 = ImageUtils.resizeIconTo( HOME_ICON, 300,300);
	

	public static final ImageIcon DOC = new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-doctor-64.png") );
	public static final ImageIcon DOC_50_50 = ImageUtils.resizeIconTo( DOC, 300,300);
	
	public static final ImageIcon AJOUTER_PATIENT= new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-patient-64(1).png") );
	public static final ImageIcon  AJOUTER_PATIENT_50_50 = ImageUtils.resizeIconTo( AJOUTER_PATIENT,60,60);
	
	public static final ImageIcon MODIFIER_PATIENT= new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-modifier-l'utilisateur-homme-64.png") );
	public static final ImageIcon MODIFIER_PATIENT_50_50 = ImageUtils.resizeIconTo( MODIFIER_PATIENT,60,60);
	
	public static final ImageIcon SUPPRIMER_PATIENT= new ImageIcon( IconConstants.class.getResource("/resources/icons/icons8-supprimer-l'utilisateur-homme-64.png") );
	public static final ImageIcon  SUPPRIMER_PATIENT_50_50 = ImageUtils.resizeIconTo( SUPPRIMER_PATIENT,60,60);
	
	public static final ImageIcon HOPITAL= new ImageIcon( IconConstants.class.getResource("/resources/icons/pharmacy.png") );
	public static final ImageIcon  HOPITAL_200_200 = ImageUtils.resizeIconTo( HOPITAL,200,200);
	
	
	
}
