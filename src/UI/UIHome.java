package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.utils.IconConstants;
import ui.utils.Mylib;

public class UIHome extends JFrame {
	private static final long serialVersionUID = 1L;
	
//	Les attributs : composants internes ....
	private JPanel pWrapper;
	private JPanel pHeader;
	private JPanel pSide;
	private JPanel pIntern;
	 int oldWidth =0;
	
	public static JLabel lHome;
	public static JLabel lGp;
	public static JLabel lGRdv;
	public static JLabel lDossier;
	public static JLabel lNotes;
	public static JLabel lSettings;
	public static JLabel lAbout;
	public static JLabel lDeconnex;
	public static JLabel lTitre;
	public static JLabel lside;
	//pas ecore utilisée
	public static JLabel lClock;
	private JLabel lMotiv;
	public static JLabel lExit;
	public static JLabel hopital;
	
	private static UIPatientPanel panelPatient =null;
	private static UIHeader uiheader =null;
	private static UISide uiSide =null;
	private static UIRendezvousPanel panelRendezVous=null;
	private static UIConsultationPanel panelConsu=null;
	private static UIHome singlton = null;
	
	public static UIHome getSinglton() {
		if (singlton == null) {
			singlton = new UIHome();
		}

		return singlton;
	}
	
	
	
	
//	Les méthodes ....
	private UIHome() {
		
		initUI();		
		updateLayout();
		handleEvents();
		
				
	}
	
	private void initUI() {
//		Instancier les composants internes et les paramétrer ...
		getUIRendezVous();
		getUIPatientPanel();
		getUIConsu();
		pWrapper = new JPanel();
	    pHeader = new JPanel() {
	        private static final long serialVersionUID = 1L;

			@Override
	        protected void paintComponent(Graphics grphcs) {
	            super.paintComponent(grphcs);
	            Graphics2D g2 = (Graphics2D) grphcs;
	            g2.setPaint(Color.white);
	            Color color1= new Color(153, 102, 255);
	            Color color2= new Color(102, 179, 255);
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                    RenderingHints.VALUE_ANTIALIAS_ON);
	            GradientPaint gp = new GradientPaint(5, 7,
	            		color1, 500,700,
	            		color2);
	            g2.setPaint(gp);
	            g2.fill(new RoundRectangle2D.Double(5, 7, 300, 400, 10, 10));
	            
	            g2.fillRect(0, 0, getWidth(), getHeight()); 
	            

	        }

	    };
	    
	    

		pSide = new JPanel(); 
		pSide.setBackground(new Color(153, 194, 255));
		
		pIntern = new JPanel();
		pIntern.setBackground(new Color(153, 153, 255));
		pWrapper.setBorder( BorderFactory.createLineBorder(Color.gray,1) );
		
		
		//initialisation des labels
		lHome = Mylib.createLabelIcon("    Home",IconConstants.HOME_ICON_50_50);
		lGp= Mylib.createLabelIcon("   Patients",IconConstants.GP_ICON_50_50);
		lGRdv= Mylib.createLabelIcon("   Rendez-Vous",IconConstants.GR_ICON_50_50);
		lDossier= Mylib.createLabelIcon("   Dossiers patients",IconConstants.DP_ICON_50_50);
		lNotes= Mylib.createLabelIcon("   Notes",IconConstants.NOTES_ICON_50_50);
		lSettings= Mylib.createLabelIcon("   Paramètres",IconConstants.SETTINGS_ICON_50_50);
		lAbout= Mylib.createLabelIcon("   About us...",IconConstants.ABOUT_ICON_50_50);
		lDeconnex= Mylib.createLabelIcon("   Déconnexion",IconConstants.DECONNEX_ICON_50_50);
		lTitre= Mylib.createLabelIconSimple("Gestion cabinet médical",null);
		hopital = Mylib.createLabelIcon("",IconConstants.HOPITAL_200_200);
		lTitre.setForeground(Color.DARK_GRAY);
		lTitre.setFont(new Font("Monospaced", Font.BOLD ,30));
		lside= Mylib.createLabelIconSimple("",IconConstants.SIDE_50_50);
		lClock= Mylib.createLabelIconSimple("",IconConstants.ENTER_ICON_30_30);
		lExit= Mylib.createLabelIconSimple("",IconConstants.EXIT_ICON_50_50);
		lMotiv = Mylib.createLabelIconSimple("   ",null);
		lMotiv.setText("<html>The aim of medicine is to prevent desease and prolong life ,The ideal of medicine is to eliminate the need of a physician</html>");
		lMotiv.setBackground(new Color(185, 153, 255));
		lMotiv.setFont(new Font("Monospaced",Font.ITALIC ,35));

//		pSide.setVisible(false);
		this.setContentPane(pWrapper);
		this.setSize(1400,1000);
        this.setLocationRelativeTo(null);
	
		this.setTitle("Gestion des Patients");
		//this.setMinimumSize(this.getSize());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialisation de pInterne
		pIntern.removeAll();
	    GroupLayout  layout = new GroupLayout(pIntern);
		pIntern.setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				
		);
     	layout.setVerticalGroup(layout.createSequentialGroup()
     		
		);
     	
		
     	
		

	}
	
//	Gerer la dispoistion (layout management) : placer les composants internes sur le container ...
	  public void updateLayout() {
	    	int newwidth =resize();
			
			if(newwidth>1200&& oldWidth < 1200) {
				layoutUI1();
				
				
			}else if (newwidth < 1200 && oldWidth >= 1200) {
				layoutUI2();
				
			}
			oldWidth = newwidth;
			

	    	
	 }
	private void layoutUI1() {
		showHeader();
		handleEventsSide();
		uiheader.layoutUI1();
		getUIPatientPanel().layout1();
		
		
     	    lHome.setText("    Home");
		    lGp.setText("   Patients");
			lGRdv.setText("   Rendez-Vous");
			lDossier.setText("   Dossiers patients");
			lNotes.setText("   Notes");
			lSettings.setText("   Paramètres");
			lAbout.setText("   About us...");
			lDeconnex.setText("   Déconnexion");
     	
     	
	    GroupLayout   layout = new GroupLayout(pSide);
        pSide.setLayout(layout);
		
		  //Spécifier la vue horizontalement ....
		layout.setHorizontalGroup( layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addGap(335)
								.addComponent(lExit)

							
						)
						
						.addGroup(layout.createSequentialGroup()
								.addGap(95)
								.addComponent(hopital)

							
						)
						.addComponent(lHome,400,400,550)
						.addComponent(lGp,400,400,550)
						.addComponent(lGRdv,400,400,550)
						.addComponent(lDossier,400,400,550)
						.addComponent(lNotes,400,400,550)
						.addComponent(lSettings,400,400,550)
						.addComponent(lAbout,400,400,550)
						.addComponent(lDeconnex,400,400,550)
						
				)
				
				
				
		);
		
		//Spécifier la vue verticalement ....
		layout.setVerticalGroup( layout.createSequentialGroup() 
				
				.addComponent(lExit)
				.addGap(10,20,Short.MAX_VALUE)
				.addComponent(hopital)
				.addGap(0,20,60)
				.addComponent(lHome,50,50,70)
				.addComponent(lGp,50,50,100)
				.addComponent(lGRdv,50,50,100)
				.addComponent(lDossier,50,50,100)
				.addComponent(lNotes,50,50,100)
				.addComponent(lSettings,50,50,100)
				.addComponent(lAbout,50,50,100)
				.addComponent(lDeconnex,50,50,100)
				.addGap(20,100,Short.MAX_VALUE)					
			
		);
		
     
		
		pWrapper.removeAll();
		layout =new GroupLayout(pWrapper);
		pWrapper.setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(pSide,400,400,400)
				.addGroup(layout.createParallelGroup()
						.addComponent(pHeader,100,100,Short.MAX_VALUE)
						.addComponent(pIntern,100,100,Short.MAX_VALUE)
					
				)
							
		);
		
		
		layout.setVerticalGroup(layout.createParallelGroup()
				.addComponent(pSide,100,200,Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addComponent(pHeader,100,100,100)
						.addComponent(pIntern,100,100,Short.MAX_VALUE)
					
                         
				)
		);
		
		this.setLocationRelativeTo(null);
		
		
		
		
	}
	
	
	private void layoutUI2() {
		showHeader();
		uiheader.layoutUI2();
		handleEventsHeader();
		getUIPatientPanel().layout2();
		pSide.setVisible(false);
 	
      
		
      
		
		 pWrapper.removeAll();
		GroupLayout layout =new GroupLayout(pWrapper);
		pWrapper.setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(pHeader,100,100,Short.MAX_VALUE)
						.addComponent(pIntern,100,100,Short.MAX_VALUE)
					
				)
							
		);
		
		
		layout.setVerticalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(pHeader,100,100,100)
						.addComponent(pIntern,100,100,Short.MAX_VALUE)
					
                         
				)
		);
		
		this.setLocationRelativeTo(null);
		
		
		
		
	}



	


	
//	Traitement des évènements : ajouter des listeners ... 
	private void handleEvents() {
	
	
	lGp.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
							
		         showPanel(getUIPatientPanel(),"");
		        
		         
				
			}
		});
	 
	  lGRdv.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
		        showPanel(getUIRendezVous(),"");
		       
		        
		         
				
			}
		});
	  

	  lDossier.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
		        showPanel(getUIConsu(),"");
		       
		        
		         
				
			}
		});
	  lside.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
			 //  showPanelS(getUISide(),"");
			   pSide.setVisible(true);
			   
			
				
			}
		});

	  
	  
	  
	  
	  
	  
	  
	  
	 //*****************ui rendez vous cest originalememnt pour patient afin dacceder a la liste des consultations
//	  getUIRendezVous().table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				
//				   
//					if(e.getClickCount()==2) {
//					  int index = getUIRendezVous().table.getSelectedRow();
//					  getUIPatientPanel().index =getUIRendezVous().model.getValueAt(index,0).toString(); //recuperre l'id
//					  getUIPatientPanel().azul.setText(getUIPatientPanel().index);   
//					  showPanel(getUIPatientPanel(), "");
//					  
//					    
//					}
//					
//					
//				
//				
//			}
//		});
//			
		
        
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
					updateLayout();
			        


					
            }
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		

		
		lDeconnex.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lDeconnex.setForeground(Color.darkGray);

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lDeconnex.setForeground(Color.RED);
			}
		});
		
		
	    lExit.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				pSide.setVisible(false);
			}
			
		});
	       
		
	}
	
    public int resize() {
		return this.getWidth();
	}
    
  

//	Les méthodes assiciées aux listeners ...
	public void showPanel(JPanel panel, String title) {
		this.pIntern.removeAll();
		this.pIntern.updateUI();
		
		this.setTitle(title);
		
		if (panel == null) {
			return;
		}
		
		GroupLayout layout = (GroupLayout) pIntern.getLayout();
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				
				.addComponent(panel,100,100,Short.MAX_VALUE)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				
				.addComponent(panel,100,100,Short.MAX_VALUE)
				
		);
	}
	public void showPanelH(JPanel panel, String title) {
		this.pHeader.removeAll();
		this.pHeader.updateUI();
		
		this.setTitle(title);
		
		if (panel == null) {
			return;
		}
		
		GroupLayout layout = new GroupLayout(pHeader);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				
				.addComponent(panel,100,100,Short.MAX_VALUE)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				
				.addComponent(panel,100,100,Short.MAX_VALUE)
				
		);
	}
	
	public void showPanelS(JPanel panel, String title) {
		this.pSide.removeAll();
		this.pSide.updateUI();
		
		this.setTitle(title);
		
		if (panel == null) {
			return;
		}
		
		GroupLayout layout = new GroupLayout(pSide);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				
				.addComponent(panel,100,100,Short.MAX_VALUE)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				
				.addComponent(panel,100,100,Short.MAX_VALUE)
				
		);
	}
	

	public UIHeader getUIHeader() {
		if (uiheader == null) {
			uiheader = new UIHeader();
	
			
		}
		
		return uiheader;
	}
	
	public UISide getUISide() {
		if (uiSide == null) {
			uiSide = new UISide();
	
			
		}
		
		return uiSide;
	}

	
	
 	public static UIPatientPanel getUIPatientPanel() {
		if (panelPatient == null) {
			panelPatient = new UIPatientPanel();
			panelPatient.setBackground(Color.white);
			
		}
		
		return panelPatient;
	}
	
	public UIRendezvousPanel  getUIRendezVous() {
		if (panelRendezVous == null) {
			panelRendezVous = new UIRendezvousPanel();
			panelRendezVous.setBackground(Color.white);
			
		}
		else {
			panelRendezVous.getuiPanel();
		}
		
		return panelRendezVous;
		
	}
	
	public UIConsultationPanel  getUIConsu() {
		if (panelConsu == null) {
			panelConsu = new UIConsultationPanel();
			panelConsu.setBackground(Color.white);
			
		}
		
		
		return panelConsu;
		
	}
	
	
	public void showPanelCRUDUtlisateur() {
		showPanel( getUIPatientPanel()," Gestion des Utilisateurs " );
	}
	public void showHeader() {
		showPanelH(getUIHeader(),"");
	}
	
	
	public void handleEventsSide(){
		
		lGp.setOpaque(false);
	    lGRdv.setOpaque(false);					
		lHome.setOpaque(false);
		lDossier.setOpaque(false);
		lNotes.setOpaque(false);
		lSettings.setOpaque(false);
		lAbout.setOpaque(false);
		
	      lGp.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					lGp.setOpaque(true);
					lGp.setBackground(new Color(102, 179, 255));
			        lGRdv .setBackground(new Color(153, 194, 255));
			        lDossier.setBackground(new Color(153, 194, 255));
	 				lNotes.setBackground(new Color(153, 194, 255));
	 				lSettings.setBackground(new Color(153, 194, 255));
	 				lAbout.setBackground(new Color(153, 194, 255));
	 				lHome.setBackground(new Color(153, 194, 255));
					
				}
			});
		 
	       lGRdv.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					lGRdv.setOpaque(true);
					lGRdv.setBackground(new Color(102, 179, 255));
			        lGp .setBackground(new Color(153, 194, 255));  
			        lDossier.setBackground(new Color(153, 194, 255));
	 				lNotes.setBackground(new Color(153, 194, 255));
	 				lSettings.setBackground(new Color(153, 194, 255));
	 				lAbout.setBackground(new Color(153, 194, 255));
	 				lHome.setBackground(new Color(153, 194, 255));
			       
			        
			         
					
				}
			});
	       
	 	  
	 	  lHome.addMouseListener(new MouseAdapter() {
	 			
	 			@Override
	 			public void mouseClicked(MouseEvent arg0) {
	 				lHome.setOpaque(true);
					lHome.setBackground(new Color(102, 179, 255));
	 				lGp.setBackground(new Color(153, 194, 255));
	 				lGRdv.setBackground(new Color(153, 194, 255));
	 				lDossier.setBackground(new Color(153, 194, 255));
	 				lNotes.setBackground(new Color(153, 194, 255));
	 				lSettings.setBackground(new Color(153, 194, 255));
	 				lAbout.setBackground(new Color(153, 194, 255));
	 				
	 		        showPanel(getUIRendezVous(),"");
	 		       
	 		        
	 		         
	 				
	 			}
	 		});
	 	  
	 	  lDossier.addMouseListener(new MouseAdapter() {
	 			
	 			@Override
	 			public void mouseClicked(MouseEvent arg0) {
	 				lDossier.setOpaque(true);
					lDossier.setBackground(new Color(102, 179, 255));
	 				lGp.setBackground(new Color(153, 194, 255));
	 				lGRdv.setBackground(new Color(153, 194, 255));
	 				lHome.setBackground(new Color(153, 194, 255));
	 				lNotes.setBackground(new Color(153, 194, 255));
	 				lSettings.setBackground(new Color(153, 194, 255));
	 				lAbout.setBackground(new Color(153, 194, 255));
	 				
	 				showPanel(getUIConsu(),"");
	 		       
	 		        
	 		         
	 				
	 			}
	 		});
	 	  
	 	  lNotes.addMouseListener(new MouseAdapter() {
	 			
	 			@Override
	 			public void mouseClicked(MouseEvent arg0) {
	 				lNotes.setOpaque(true);
					lNotes.setBackground(new Color(102, 179, 255));
	 				lGp.setBackground(new Color(153, 194, 255));
	 				lGRdv.setBackground(new Color(153, 194, 255));
	 				lHome.setBackground(new Color(153, 194, 255));
	 				lDossier.setBackground(new Color(153, 194, 255));
	 				lSettings.setBackground(new Color(153, 194, 255));
	 				lAbout.setBackground(new Color(153, 194, 255));
	 				
	 		        showPanel(getUIRendezVous(),"");
	 		       
	 		        
	 		         
	 				
	 			}
	 		});
	 	  lSettings.addMouseListener(new MouseAdapter() {
	 			
	 			@Override
	 			public void mouseClicked(MouseEvent arg0) {
	 				lSettings.setOpaque(true);
					lSettings.setBackground(new Color(102, 179, 255));
	 				lGp.setBackground(new Color(153, 194, 255));
	 				lGRdv.setBackground(new Color(153, 194, 255));
	 				lHome.setBackground(new Color(153, 194, 255));
	 				lDossier.setBackground(new Color(153, 194, 255));
	 				lNotes.setBackground(new Color(153, 194, 255));
	 				lAbout.setBackground(new Color(153, 194, 255));
	 				
	 		        showPanel(getUIRendezVous(),"");
	 		       
	 		        
	 		         
	 				
	 			}
	 		});
	 	  lAbout.addMouseListener(new MouseAdapter() {
	 			
	 			@Override
	 			public void mouseClicked(MouseEvent arg0) {
	 				lAbout.setOpaque(true);
					lAbout.setBackground(new Color(102, 179, 255));
	 				lGp.setBackground(new Color(153, 194, 255));
	 				lGRdv.setBackground(new Color(153, 194, 255));
	 				lHome.setBackground(new Color(153, 194, 255));
	 				lDossier.setBackground(new Color(153, 194, 255));
	 				lSettings.setBackground(new Color(153, 194, 255));
	 				lNotes.setBackground(new Color(153, 194, 255));
	 				
	 		        showPanel(getUIRendezVous(),"");
	 		       
	 		        
	 		         
	 				
	 			}
	 		});
	 	  
	 	  
			
	       
			
		}
	public void handleEventsHeader(){
		
		lGp.setOpaque(false);
	    lGRdv.setOpaque(false);					
		lHome.setOpaque(false);
		lDossier.setOpaque(false);
		lNotes.setOpaque(false);
		lSettings.setOpaque(false);
		lAbout.setOpaque(false);
	      lGp.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					lGp.setOpaque(false);
					
					
				}
			});
		 
	       lGRdv.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					lGRdv.setOpaque(false);
					
			       
			        
			         
					
				}
			});
	       
	 	  
	 	  lHome.addMouseListener(new MouseAdapter() {
	 			
	 			@Override
	 			public void mouseClicked(MouseEvent arg0) {
	 				lHome.setOpaque(false);
					
	 		        showPanel(getUIRendezVous(),"");
	 		       
	 		        
	 		         
	 				
	 			}
	 		});
	 	  
	 	  lDossier.addMouseListener(new MouseAdapter() {
	 			
	 			@Override
	 			public void mouseClicked(MouseEvent arg0) {
	 				lDossier.setOpaque(false);
					
	 				
	 		       
	 		        
	 		         
	 				
	 			}
	 		});
	 	  
	 	  lNotes.addMouseListener(new MouseAdapter() {
	 			
	 			@Override
	 			public void mouseClicked(MouseEvent arg0) {
	 				lNotes.setOpaque(false);
					
	 				
	 		        showPanel(getUIRendezVous(),"");
	 		       
	 		        
	 		         
	 				
	 			}
	 		});
	 	  lSettings.addMouseListener(new MouseAdapter() {
	 			
	 			@Override
	 			public void mouseClicked(MouseEvent arg0) {
	 				lSettings.setOpaque(false);
					
	 		        showPanel(getUIRendezVous(),"");
	 		       
	 		        
	 		         
	 				
	 			}
	 		});
	 	  lAbout.addMouseListener(new MouseAdapter() {
	 			
	 			@Override
	 			public void mouseClicked(MouseEvent arg0) {
	 				lAbout.setOpaque(false);
					
	 		        showPanel(getUIRendezVous(),"");
	 		       
	 		        
	 		         
	 				
	 			}
	 		});
	 	  
	 	  
			
	       
			
		}
	
	
}
