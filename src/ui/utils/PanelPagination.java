package ui.utils;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelPagination extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton bFirst, bPrev, bNext, bLast;
	
	public PanelPagination() {
		initUI();
		layoutUI();
		handeEvents();
	}
	
	private void initUI() {
		bFirst = ButtonFacotry.createFirstButton();
		bPrev = ButtonFacotry.createPreviousButton();
		bNext = ButtonFacotry.createNextButton();
		bLast = ButtonFacotry.createLastButton();
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
	
	private void layoutUI() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(3)
				.addComponent(bFirst, 28, 28, 28)
				.addComponent(bPrev, 28, 28, 28)
				.addGap(3, 3, Short.MAX_VALUE)
				.addComponent(bNext, 28, 28, 28)
				.addComponent(bLast, 28, 28, 28)
				.addGap(3)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup()
				.addGap(0, 0, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup()
						.addComponent(bFirst, 28, 28, 28)
						.addComponent(bPrev, 28, 28, 28)
						.addComponent(bNext, 28, 28, 28)
						.addComponent(bLast, 28, 28, 28)
				)
				.addGap(0, 0, Short.MAX_VALUE)
		);
	}
	
	private void handeEvents() {
		
	}
}
