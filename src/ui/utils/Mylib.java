package ui.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class Mylib {
	
	
	private static FocusListener focusListener = new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent e) {
			
		((JComponent)e.getSource()).setBackground(Color.white);
			
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			((JComponent)e.getSource()).setBackground(Color.YELLOW);
			
		}
	};
	
	private static MouseListener mouseListener = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
		
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			((JComponent)e.getSource()).setForeground(Color.DARK_GRAY);
			((JComponent)e.getSource()).setFont(new Font("Monospaced",Font.ITALIC, 20));
			
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			((JComponent)e.getSource()).setForeground(new Color(102, 26, 255));
			((JComponent)e.getSource()).setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC,25));
			
		}
		
	};
	
	public static JTextField createTextField() {
		JTextField fJTextField = new JTextField();
		//fJTextField.addFocusListener(focusListener);
		return fJTextField;
		
		
		
	}
	public static JLabel createitalicfont(String Content) {
		JLabel label = new JLabel(Content);
		label.setFont(new Font("Serif",Font.ITALIC, 20));
        label.setBackground(Color.blue);
		return label;	
		
	}
	
	
	public static JLabel createLabel(String content) {
		JLabel label = new JLabel(content);
		label.setFont(new Font("Monospaced",0, 20));
		return label;
	}
	public static JLabel createLabelIcon(String content,Icon icon) {
		JLabel label = new JLabel(content);
		label.setFont(new Font("Monospaced",Font.ITALIC, 20));
		//to eb able to set the backgroud for the label
		label.setIcon(icon);
		label.addMouseListener(mouseListener);
		return label;
	}
	
	
	public static JLabel createLabelIconSimple(String content,Icon icon) {
		JLabel label = new JLabel(content);
		label.setFont(new Font("Monospaced",Font.ITALIC, 20));
		label.setIcon(icon);
		
		
		return label;
	}
	
	public static JButton createButton(String text, Icon icon) {
		JButton button = new JButton(text);
		button.setFont(new Font("Monospaced", Font.ITALIC, 15));
		button.setIcon(icon);
		button.setOpaque(false);
		button.setBorderPainted(false);
		button.setForeground(new Color(172, 0, 230));
		button.setFocusPainted(false);
		button.setBackground(new Color(153, 194, 255));
	
		return button;
	}
	public static void createTable(JTable table) {
		TableColumnModel column = table.getColumnModel();
       
		table.setFocusable(false);
		//table.setIntercellSpacing(null);
		table.setRowHeight(25);
		table.setSelectionBackground(new Color(153, 194, 255));
		table.setShowVerticalLines(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setFont(new Font("Serif",Font.ITALIC,14));
		
		table.getTableHeader().setOpaque(false);
		table.setBackground(Color.white);
		table.getTableHeader().setFont(new Font("Serif",Font.ITALIC|Font.BOLD,14));
		table.getTableHeader().setBackground(new Color(153, 102, 255)); 
		table.getTableHeader().setForeground(new Color(255,255, 255)); 
	
		
		
	}
	
	//FCT pour le filtre dns un tableau 
	@SuppressWarnings("unused")
	public static void recherche(JTable table,DefaultTableModel model,JTextField tRecherche) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(tRecherche.getText().trim()));
		
	}
	
	//fct pour aller a lautre text field lorsque on clique sur entrer
	public static void TextNext(JTextField tField , JTextField nextField) {
		tField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) 
			    {
			    	nextField.requestFocus();
			    }
				
			}
		});
	}
	
	
	


}
