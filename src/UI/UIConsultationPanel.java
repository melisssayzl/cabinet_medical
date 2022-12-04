package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import modele.beans.Consultation;
import modele.beans.Patient;
import models.dao.DAOConsultation;
import models.dao.DAOPatient;
import models.dao.DAOUser;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import ui.utils.IconConstants;
import ui.utils.Mylib;
import ui.utils.PanelPagination;

public class UIConsultationPanel extends JPanel {
	private static final long serialVersionUID = 1L;

//	Les attributs : composants internes ....
//	private JTextField tNumConsu;
//	private JTextField tRecherche;
//	
    private UtilDateModel modelDatePR = new UtilDateModel();
//    private JDatePanelImpl datePrise = new JDatePanelImpl(modelDatePR);
//    private JDatePickerImpl datePickerPrise = new JDatePickerImpl(datePrise);

	private List<Consultation> listConsultation;
	private List<Patient> listPatients;
	public static DefaultTableModel model;
	public static JTable table;
	private Object[] row;
	private Object[] columns = { "id", "N° consu", "date"};
	private JScrollPane scroll;

	private JButton bAjouter;
	private JButton bModifier;
	private JButton bSupprimer;
	
	private JPanel pLeftSide;
	private JPanel pContent;
	
	
	private JScrollPane	spListItems;
	public static JList<Patient>	listItems;
	public static DefaultListModel<Patient> dlmListItems;
	
	private JPopupMenu 	pmListItems;
    private JMenuItem 	miAdd, miDelete,miModify;
	
	private PanelPagination	panelPagination;

	

//	Les méthodes ....
	public UIConsultationPanel() {
		initUI();
		layoutUI();
		handleEvents();
	}

	
	
	@SuppressWarnings("serial")
	private void initUI() {
		
	
//		tNumConsu = new JTextField();
//		tRecherche = new JTextField();

	
		
		
		bAjouter = Mylib.createButton("",IconConstants.AJOUTER_PATIENT_50_50);
		bModifier = Mylib.createButton("", IconConstants.MODIFIER_PATIENT_50_50);
		bSupprimer = Mylib.createButton("",IconConstants.SUPPRIMER_PATIENT_50_50 );
		
		scroll = new JScrollPane();
		
		//Le contenu global de la fenetre ...
				pLeftSide = new JPanel();
				pContent = new JPanel();
				pContent.setBackground(Color.white);
				panelPagination = new PanelPagination();
				
				pLeftSide.setBorder(BorderFactory.createEtchedBorder());
				pContent.setBorder(BorderFactory.createEtchedBorder());
		
				dlmListItems = new DefaultListModel<>();
			
				listItems = new JList<>(dlmListItems) ;
				
				spListItems = new JScrollPane(listItems);
				pmListItems = new JPopupMenu();
				miAdd = new JMenuItem("Ajouter ordonnance");
				miDelete = new JMenuItem("Supprimer ordonnance");
				miModify = new JMenuItem("Modifier ordonnance");

				
				pmListItems.add(miAdd);
				pmListItems.addSeparator();
				pmListItems.add(miDelete);
				pmListItems.addSeparator();
				pmListItems.add(miModify);
				
		

        //Recuperation de la liste des patients
		listConsultation = new ArrayList<>();
		listPatients = DAOPatient.getList();
		
        //Tous ce qui concerne l'instanciation du jtable
		table = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				for (int i = 0; i < table.getRowCount(); i++) {
					if (row==i) {
						return false;
					}
				}
				return true;
			}
		};
		
		// les colonnes du jtable
		row = new Object[3];
		table.setBackground(new Color(240, 248, 236));
		//l'ajout du scroll au jtable
		scroll.setViewportView(table);
		
		// le modele du tableau
		model = new DefaultTableModel();
		// set the column on top of the table
		model.setColumnIdentifiers(columns);
		// set the model to the table
		table.setModel(model);
		
		listItems.setSelectionBackground(new Color(153, 194, 255));
		Mylib.createTable(table);	
		fillJlist(listPatients);
  
        this.setSize(500,400);
		
		
	}

//	Gerer la dispoistion (layout management) : placer les composants internes sur le container ...
	
	private void layoutUI() {
		layoutpContent();
		layoutPLeftSide();
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(5)
				.addComponent(pLeftSide,250,275,300)
				.addGap(5)
				.addComponent(pContent, 400, 400, Short.MAX_VALUE)
				.addGap(5)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup()
				
						.addComponent(pLeftSide, 300, 300, Short.MAX_VALUE)
						.addComponent(pContent, 300, 300, Short.MAX_VALUE)
				
				.addGap(5)
		);
		
		
	}
	
	public void layoutpContent() {
		
		GroupLayout layout = new GroupLayout(pContent);
		pContent.setLayout(layout);

		// Spécifier la vue horizontalement ....
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGap(20,20,Short.MAX_VALUE)
				
				.addGroup(layout.createSequentialGroup()
				        .addGap(30,100,Short.MAX_VALUE)
						.addComponent(bAjouter,63,63,63)
						.addGap(5,20,30)
						.addComponent(bSupprimer,63,63,63)
						.addGap(5,20,30)
						.addComponent(bModifier,63,63,63)
						.addGap(30,100,Short.MAX_VALUE)
				        
						
				)
				.addGroup(layout.createSequentialGroup()
						.addGap(20,100,Short.MAX_VALUE)
						.addComponent(scroll,400,700,900)
						.addGap(20,100,Short.MAX_VALUE)

				)		
						
						

		);

		// Spécifier la vue verticalement ....
		layout.setVerticalGroup(layout.createSequentialGroup()
				                .addGap(20,100,Short.MAX_VALUE)
								
								.addGroup(layout.createParallelGroup()
										
										.addComponent(bAjouter,63,63,63)
										.addComponent(bSupprimer,63,63,63)
										.addComponent(bModifier,63,63,63)	
								)
								.addGap(15,20,35)
								.addComponent(scroll,200,300,300)
								.addGap(20,100,Short.MAX_VALUE)
						

			
		);

	}
	private void layoutPLeftSide() {
		GroupLayout layout = new GroupLayout(pLeftSide);
		pLeftSide.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(2)
				.addGroup(layout.createParallelGroup()
						
						.addComponent(spListItems, 50, 50, Short.MAX_VALUE)
						.addComponent(panelPagination, 50, 50, Short.MAX_VALUE)
				)
				.addGap(2)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				
				.addGap(5)
				.addComponent(spListItems, 100, 100, Short.MAX_VALUE)
				.addGap(5)
				.addComponent(panelPagination, 30, 30, 30)
				.addGap(2)
		);
		
		
//		dlmListItems = new DefaultListModel<>();
//		listItems = new JList<>(dlmListItems) ;
//		panelPagination = new PanelPagination();
	}
	
	


	
//	Traitement des évènements : ajouter des listeners ... 

	private void handleEvents() {
		
		
		
		

		FocusListener fl = new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				((JComponent) e.getSource()).setBackground(Color.WHITE);

			}

			@Override
			public void focusGained(FocusEvent e) {
				((JComponent) e.getSource()).setBackground(new Color(240, 248, 236));

			}

		};
	
		
		

		// Les actions des boutons
		// Le bouton ajouter
		bAjouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			bAjouterClicked();
				
				

			}

		});


		// le bouton supprimer
		bSupprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			 //   bSupprimerclicked();
				
				

			}
		});
		
		
		
		
	
		miAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					new UIOrdonnanceFrame().setVisible(true);
					
				}
				
			
		});
		
		
		
		//Delete all rows of Jtable 
//		 model.getDataVector().removeAllElements();


		listItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 model.setNumRows(0);	
			     Patient  p=listItems.getSelectedValue();
			     fillJtable(p.getId());
			    
					
				
			}
		});
		
				
		
	  

		// remplir les champs du tf avec l'element sélectionné du tableau

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				
				if(row>=0) {
					
					listItemsMouseClicked(e);
					
				}else {
					JOptionPane.showMessageDialog(table,"Veillez selectionner ue ligne svp ...","Erreur sélectionnez une ligne ",JOptionPane.ERROR_MESSAGE);					
				}


			}
		});
		

	}
	


	
//	Les méthodes assiciées aux listeners ...
	
	
	private void fillJtable(Long idPatient) {
		listConsultation = DAOConsultation.getListByID(idPatient);
		Iterator<Consultation> it = listConsultation.iterator();
		
		while (it.hasNext()) {
			Consultation rdv = (Consultation) it.next();
			row[0] = rdv.getId();
			row[1] = rdv.getNumConsultation();
			row[2]= rdv.getDate();

			model.addRow(row);
			
		}
	}

	private String GenerateNumConsultation() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Random random = new Random();
		String autoNum = formatter.format(Calendar.getInstance().getTime());
		String autString;
		int upperbound1 = 9;
		int upperbound2 = 9;
		int upperbound3 = 9;
		int upperbound4 = 9;
		int upperbound5 = 9;
		int upperbound6 = 9;
		int upperbound7 = 9;
		
		return autString = autoNum
				+""+random.nextInt(upperbound1)
				+""+random.nextInt(upperbound2)
				+""+random.nextInt(upperbound3)
				+""+random.nextInt(upperbound4)
				+""+random.nextInt(upperbound5)
				+""+random.nextInt(upperbound6)
				+""+random.nextInt(upperbound7);
		
	}
	private void bAjouterClicked() {

		String autoGeneratedNumber = GenerateNumConsultation();
		Calendar today =  Calendar.getInstance();
		String dateS= String.format("%1$tY-%1$tm-%1$td",today.getTime());
		Date date = Date.valueOf(dateS);
		Patient  p =listItems.getSelectedValue();
		
		
		Consultation consultation = new Consultation(null,autoGeneratedNumber,p.getId(),date,DAOUser.getMedecin().getId());
		DAOConsultation.insert(consultation);
		
	// Le premier controle : les champs vides ..

		row[0] = consultation.getId() ;
		row[1] = autoGeneratedNumber ;
		row[2] = today.getTime();
		
		model.addRow(row);
	

		JOptionPane.showMessageDialog(this, "Consultation inséré ...");

	}

	
	private void bSupprimerclicked() {
		int row = table.getSelectedRow();
		if (row >= 0) {
			// si row est inferieur a zero alors aucune ligne n'a été sélectionnée
			long id = Long.parseLong(model.getValueAt(table.getSelectedRow(), 0).toString());
			Consultation p = DAOConsultation.getByID(id);
			int yes = JOptionPane.showConfirmDialog(this, "Voulez vous supprimer ce rendez vous ?", "Supprimer",
					JOptionPane.YES_NO_OPTION);
			if (yes == JOptionPane.YES_OPTION) {
				
			//	cbRecherche.removeItemAt(table.getSelectedRow());
				// suppression dans la bdd
				DAOConsultation.delete(id);
				// suppression au jtable
				model.removeRow(row);
				
			
				
			}

		} else {
			JOptionPane.showMessageDialog(this, "Veuillez selctionner une ligne s'il vous plait ...");
		}

	}
	
	
	
	private void fillJlist(List<Patient> listeQQ) {
	
		Iterator<Patient> it = listeQQ.iterator();
		while (it.hasNext()) {
			 
			dlmListItems.addElement(it.next());
			
			
		}
	
	}
	
	private void listItemsMouseClicked(MouseEvent e) {
		//System.out.println(e.getButton() + "-  "+MouseEvent.BUTTON1+" - "+MouseEvent.BUTTON2);
		
		if (e.getButton() == MouseEvent.BUTTON3) {
			pmListItems.show(e.getComponent(), e.getX(), e.getY());
		}
	}


	

}

