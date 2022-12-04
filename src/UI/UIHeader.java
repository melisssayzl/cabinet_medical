package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.utils.IconConstants;
import ui.utils.Mylib;

public class UIHeader extends JPanel{
	JPanel p;
	
	
	public UIHeader() {
		initUI();
	
	}
	
	
	public void initUI(){
		p=new JPanel();
		this.setOpaque(false);

       
	    
	}
	public void layoutUI1(){
	
		this.removeAll();
		GroupLayout layout =new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(UIHome .lside,70,70,70)
			    .addGap(193,193,Short.MAX_VALUE)
				.addComponent(UIHome .lTitre,500,500,500)
				.addGap(50,300,700)
				.addComponent(UIHome .lClock,70,70,70)
			    .addGap(5,5,Short.MAX_VALUE)
				
		);
		layout.setVerticalGroup(layout.createParallelGroup()
				
				.addGroup(layout.createSequentialGroup()
						.addGap(5)
						.addComponent(UIHome .lside,63,63,63)
				)
				
				.addGroup(layout.createSequentialGroup()
						.addGap(34)
						.addComponent(UIHome .lTitre,50,50,50)
				)
				
				.addComponent(UIHome.lClock,50,50,50)
		);
		
		
		
	}
	public void layoutUI2() {
		
		UIHome .lHome.setText("");
        UIHome. lGp.setText("");
		UIHome. lGRdv.setText("");
		UIHome .lDossier.setText("");
		UIHome .lNotes.setText("");
		UIHome .lSettings.setText("");
		UIHome .lAbout.setText("");
		UIHome .lDeconnex.setText("");
		
		this.removeAll();
		GroupLayout layout =new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(20)
				.addComponent(UIHome .lHome)
				.addGap(5)
				.addComponent(UIHome .lGp)
				.addGap(5)
				.addComponent(UIHome.lGRdv)
				.addGap(5)
				.addComponent(UIHome .lDossier)
				.addGap(5)
				.addComponent(UIHome .lNotes)
				.addGap(5)
				.addComponent(UIHome .lSettings)
				.addGap(5)
				.addComponent(UIHome .lAbout)
				.addGap(5)
				.addComponent(UIHome .lDeconnex)
				.addGap(10)
		);
		
		
		layout.setVerticalGroup(layout.createParallelGroup()
				
				.addGroup(layout.createSequentialGroup()
						.addGap(15)
						.addComponent(UIHome.lHome,50,50,50)
						.addGap(15)
				)
				
				.addGroup(layout.createSequentialGroup()
						.addGap(15)
						.addComponent( UIHome.lGp,50,50,50)
						.addGap(15)
				)
				
				.addGroup(layout.createSequentialGroup()
						.addGap(15)
						.addComponent(UIHome.lGRdv,50,50,50)
						.addGap(15)
				)
				.addGroup(layout.createSequentialGroup()
						.addGap(15)
						.addComponent(UIHome .lDossier,50,50,50)
						.addGap(15)
				)
				
				.addGroup(layout.createSequentialGroup()
						.addGap(15)
						.addComponent(UIHome .lNotes,50,50,50)
						.addGap(15)
				)
				.addGroup(layout.createSequentialGroup()
						.addGap(15)
						.addComponent(UIHome .lSettings,50,50,50)
						.addGap(15)
				)
				.addGroup(layout.createSequentialGroup()
						.addGap(15)
						.addComponent(UIHome .lAbout,50,50,50)
						.addGap(15)
				)
				.addGroup(layout.createSequentialGroup()
						.addGap(15)
						.addComponent(UIHome .lDeconnex,50,50,50)
						.addGap(15)
				)
				
				
		);
		
		
	
		
	}
	public void layoutUI3(){
		    UIHome.lHome.setText("    Home");
		    UIHome.	lGp.setText("   Patients");
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
