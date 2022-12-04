package UI;
 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.metal.MetalTabbedPaneUI;

import utils.DatabaseConfig;


//UTILISATION DE TBBED PANE
 
public class window extends JFrame{
	private static final long serialVersionUID = 1L;
	JPanel panel ;
	JPanel panel1 ;
	JPanel panel2 ;
	JPanel panel3 ;
	JPanel panel4 ;
	JPanel p;
	JTabbedPane tabbedPane ;
	public window() {
		initUI();
		layoutUI();
		handleEvents();
	}
	
	
	public void initUI(){
		
//		Instancier les composants internes et les paramétrer ...
		panel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		p = new JPanel();
        p.setBackground(Color.pink);
        // add tab with title
        panel.setBackground(Color.red);
        panel2.setBackground(Color.yellow);
        panel3.setBackground(Color.black);
        panel4.setBackground(Color.cyan);
        
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.addTab("Tab 1", panel);
		 
        // add tab with title and icon
        Icon icon = new ImageIcon("icon.gif");
        tabbedPane.addTab("Tab 2", icon, panel2);
 
        // add tab with title, icon and tooltip
        String tooltip = "This is a tab";
        tabbedPane.addTab("Tab 3", icon,UIHome.getSinglton(), tooltip);
 
        // insert tab after second tab
        int index = 2;
        tabbedPane.insertTab("Tab 4", icon,new UIPatientPanel(), tooltip, index);
 
       // this.getContentPane().add(tabbedPane);
		
//		Parametrer la fenetre ....
		this.setLocation(50, 150);
		this.setSize(450, 400);
		
		this.setTitle("Authentification");
		
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	
		
	}
	public void layoutUI(){
		GroupLayout layout = new GroupLayout(this.getContentPane());
		//Spécifier la vue horizontalement ....
				layout.setHorizontalGroup( layout.createParallelGroup()
			        .addComponent(p,Short.MAX_VALUE,Short.MAX_VALUE,Short.MAX_VALUE)
					.addComponent(tabbedPane,500,500,500)
						
				);
				
				//Spécifier la vue verticalement ....
				layout.setVerticalGroup( layout.createSequentialGroup() 
						.addComponent(p,100,100,100)
						.addComponent(tabbedPane)

						
				);
		
	}
	public void handleEvents(){
		
	}
	
    
}