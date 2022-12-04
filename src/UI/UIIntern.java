package UI;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class UIIntern extends JPanel{
	
	public UIIntern() {
		initUI();
		layoutUI1();
		
	}
	
	
	public void initUI(){
		this.setOpaque(false);
		this.setBackground(Color.white);

       
	    
	}
	public void layoutUI1(){
	
		this.removeAll();
		GroupLayout layout =new GroupLayout(this);
		this.setLayout(layout);
		
		
		
	}

	public void handleEvents(){

       
		
	}
    
    
    
    
    
    
    

}
