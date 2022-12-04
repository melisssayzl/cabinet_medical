package UI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class UISide extends JPanel{

		public UISide() {
		initUI();
		layoutUI1();	
		
	}
	
	public void initUI(){
		this.setOpaque(false);

        
		//lExit= Mylib.createLabelIconSimple("",IconConstants.EXIT_ICON_50_50);
	}
	public void layoutUI1(){
		   
		    UIHome.lHome.setText("    Home");
		    UIHome.lGp.setText("   Patients");
			UIHome.lGRdv.setText("   Rendez-Vous");
			UIHome.lDossier.setText("   Dossiers patients");
			UIHome.lNotes.setText("   Notes");
			UIHome.lSettings.setText("   Paramètres");
			UIHome.lAbout.setText("   About us...");
			UIHome.lDeconnex.setText("   Déconnexion");
			
			this.removeAll();
		    GroupLayout layout = new GroupLayout(this);
	        this.setLayout(layout);
			
			  //Spécifier la vue horizontalement ....
			layout.setHorizontalGroup( layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
							.addGroup(layout.createSequentialGroup()
									.addGap(335)
									.addComponent(UIHome.lExit)

								
							)
							
							.addGroup(layout.createSequentialGroup()
									.addGap(95)
									.addComponent(UIHome.hopital)

								
							)
							.addComponent(UIHome.lHome,400,400,550)
							.addComponent(UIHome.lGp,400,400,550)
							.addComponent(UIHome.lGRdv,400,400,550)
							.addComponent(UIHome.lDossier,400,400,550)
							.addComponent(UIHome.lNotes,400,400,550)
							.addComponent(UIHome.lSettings,400,400,550)
							.addComponent(UIHome.lAbout,400,400,550)
							.addComponent(UIHome.lDeconnex,400,400,550)
							
					)
					
					
					
			);
			
			//Spécifier la vue verticalement ....
			layout.setVerticalGroup( layout.createSequentialGroup() 
					
					.addComponent(UIHome.lExit)
					.addGap(10,20,Short.MAX_VALUE)
					.addComponent(UIHome.hopital)
					.addGap(0,20,60)
					.addComponent(UIHome.lHome,0,50,70)
					.addComponent(UIHome.lGp,0,50,100)
					.addComponent(UIHome.lGRdv,50,50,100)
					.addComponent(UIHome.lDossier,50,50,100)
					.addComponent(UIHome.lNotes,50,50,100)
					.addComponent(UIHome.lSettings,50,50,100)
					.addComponent(UIHome.lAbout,50,50,100)
					.addComponent(UIHome.lDeconnex,50,50,100)
					.addGap(20,100,Short.MAX_VALUE)					
				
			);
			
		
		    
		
	}
	public void handleEvents(){
		
     
		
       
		
	}
    
    
    
    
    
    
    

}
