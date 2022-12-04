package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.Soundbank;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import modele.beans.Patient;
import modele.beans.RendezVous;
import modele.beans.User;
import models.dao.DAOPatient;
import models.dao.DAORendezVous;
import models.dao.DAOUser;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import ui.utils.IconConstants;
import ui.utils.Mylib;

public class UIRendezvousPanel extends JPanel {
	private static final long serialVersionUID = 1L;

//	Les attributs : composants internes ....
	private UIRendezvousPanel uirendezvouspanel;
	private JTextField tObservation;
	private JTextField tRecherche;
	
    private UtilDateModel modelDatePR = new UtilDateModel();
    private JDatePanelImpl datePrise = new JDatePanelImpl(modelDatePR);
    private JDatePickerImpl datePickerPrise = new JDatePickerImpl(datePrise);
    
    
    private UtilDateModel modelDateRdv = new UtilDateModel();
    private JDatePanelImpl dateRdv = new JDatePanelImpl(modelDateRdv);
    private JDatePickerImpl datePickerRdv = new JDatePickerImpl(dateRdv);
	
	
	private List<RendezVous> listRendezVous;
	private List<Patient> listPatients;
	public static DefaultTableModel model;
	public static JTable table;
	public static Object[] row;
	private Object[] columns = 	{ "id", "nom", "prenom","observation", "datePR", "dateRD", "idPatient" };
	private JScrollPane scroll;

	private JLabel lObservation;
	private JLabel lDatePrise;
	private JLabel lRdv;
	
	
	private JButton bAjouter;
	private JButton bModifier;
	private JButton bSupprimer;
	public static JComboBox<Patient> cbRecherche;
	private JPanel pContainer;

//	Les méthodes ....
	public UIRendezvousPanel() {
		initUI();
		layoutUI();
		handleEvents();
	}

	
	
	@SuppressWarnings("serial")
	private void initUI() {
		
		
        pContainer = new JPanel();
		tObservation = new JTextField();
		tRecherche = new JTextField();

		lObservation = Mylib.createLabel("Observation : ");
		lDatePrise = Mylib.createLabel("Date de prise : ");
		lRdv = Mylib.createLabel("Date de rendez-vous : ");
		
		
		bAjouter = Mylib.createButton("",IconConstants.AJOUTER_PATIENT_50_50);
		bModifier = Mylib.createButton("", IconConstants.MODIFIER_PATIENT_50_50);
		bSupprimer = Mylib.createButton("",IconConstants.SUPPRIMER_PATIENT_50_50 );
		
		cbRecherche = new JComboBox<Patient>();
		
		
     
		scroll = new JScrollPane();
		
		
		
		
		


        //Recuperation de la liste des patients
		listRendezVous = DAORendezVous.getList();
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
		row = new Object[7];
		table.setBackground(new Color(240, 248, 236));
		//l'ajout du scroll au jtable
		scroll.setViewportView(table);
		
		// le modele du tableau
		model = new DefaultTableModel();
		// set the column on top of the table
		model.setColumnIdentifiers(columns);
		model.setRowCount(0);
		// set the model to the table
		table.setModel(model);
		
		// une fct qui place les informations du patient dans le jtable
		fillJtable(listRendezVous);
		//une fct qui place les informations du patient dans Combo Box
		cbRecherche = new JComboBox<>();
		fillCbox(listPatients);
		
		//L'autocompletion dans JCombobox pour la recherche du patient
		AutoCompleteDecorator.decorate(cbRecherche);
		
	
		Mylib.createTable(table);
		
	
		
  
        this.setSize(500,400);
		
		
	}

//	Gerer la dispoistion (layout management) : placer les composants internes sur le container ...
	

	public void layoutUI() {
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		// Spécifier la vue horizontalement ....
		layout.setHorizontalGroup(layout.createParallelGroup()
//				.addGap(20,20,Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addGap(0,100,Short.MAX_VALUE)
						.addComponent(cbRecherche,100,400,450)
						.addGap(20,100,Short.MAX_VALUE)
						)
				        .addGap(7)
				.addGroup(layout.createSequentialGroup()
						.addGap(0,100,Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup()
								
								.addComponent(lObservation)
								.addGap(5)
								.addComponent(lDatePrise)
								.addGap(5)
								.addComponent(lRdv)
								)
						.addGap(5)
						.addGroup(layout.createParallelGroup()
								.addComponent(tObservation,100,300,400)
								.addGap(5)
								.addComponent(datePickerPrise,100,250,350)
								.addGap(5)
								.addComponent(datePickerRdv,100,250,350)
								
						)
						.addGap(30,100,Short.MAX_VALUE)
						)
				 
				
				.addGroup(layout.createSequentialGroup()
				        .addGap(30,100,Short.MAX_VALUE)
				        .addComponent(tRecherche,150,200,250)
				        .addGap(5,20,50)
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
				                .addComponent(cbRecherche,30,40,40)
				                .addGap(30,30,30)
								.addGroup(
										layout.createParallelGroup()
										.addComponent(lObservation)
										.addComponent(tObservation,30,40,50)
										
										)
								.addGap(10,10,20)
								
								.addGroup(layout.createParallelGroup()
										.addComponent(lDatePrise)
										.addComponent(datePickerPrise, 20,30,30))
								.addGap(20)
								.addGroup(layout.createParallelGroup()
										.addComponent(lRdv)
										.addComponent(datePickerRdv, 20,30,30)

								)
								
								.addGap(15,20,40)
								
								.addGroup(layout.createParallelGroup()
										.addGroup(layout.createSequentialGroup()
												.addGap(16)
												.addComponent(tRecherche,20,30,30)
												.addGap(16)
										)
										
										.addComponent(bAjouter,63,63,63)
										.addComponent(bSupprimer,63,63,63)
										.addComponent(bModifier,63,63,63)	
								)
								.addGap(15,20,35)
								.addComponent(scroll,200,300,300)
								.addGap(20,100,Short.MAX_VALUE)
						

			
		);

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
		
		//key pressed : au fure a mesure qu'on tape au clavier
				tRecherche.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
					    
						recherche();
					}
				});
				


		tObservation.addFocusListener(fl);
		

		// Les actions des boutons
		// Le bouton ajouter
		bAjouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				bAjouterClicked();
				
				

			}

		});

		// le bouton modifier
		bModifier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				bModifierclicked();

			}
		});

		// le bouton supprimer
		bSupprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				bSupprimerclicked();
				
				

			}
		});
		
	
		
			
	

	
		// remplir les champs du tf avec l'element sélectionné du tableau

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fillTextFields();
                
			}
		});
		
		
		
		
	}

	
	private void recherche() {
	TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
	table.setRowSorter(trs);
	trs.setRowFilter(RowFilter.regexFilter(tRecherche.getText().trim()));
	
}




	
	
	private void fillTextFields() {
		int row = table.getSelectedRow();// la ligne sélectionnée
		long id = Long.parseLong( model.getValueAt(row,6).toString());
		tObservation.setText(model.getValueAt(row, 3).toString());
		modelDateRdv.setValue((Date) model.getValueAt(row,5));
		modelDatePR.setValue((Date) model.getValueAt(row,4));
		cbRecherche.setSelectedItem(DAOPatient.getByID(id));
		
		


	}

	
//	Les méthodes assiciées aux listeners ...
	
	public static void fillJtable(List<RendezVous> listeQQ) {
		
		Iterator<RendezVous> it = listeQQ.iterator();
		while (it.hasNext()) {
			RendezVous rdv = (RendezVous) it.next();
			
			row[0] = rdv.getId();
			row[1] = DAOPatient.getByID(rdv.getIdPatient()).getNom();
			row[2] = DAOPatient.getByID(rdv.getIdPatient()).getPrenom();
			row[3] = rdv.getObservation(); 
			row[4]=rdv.getDatePR();
			row[5]= rdv.getDateRD();
			row[6]= rdv.getIdPatient();
			model.addRow(row);
			
		}
	}
	
	
	public static void fillCbox(List<Patient> listQQ)
	{

		Iterator<Patient> it = listQQ.iterator();
		while (it.hasNext()) {
			Patient p = (Patient) it.next();
			cbRecherche.addItem(p);
		}
		
	}
	
	

	private void bAjouterClicked() {

		String observation = tObservation.getText().trim();
		String datePR= String.format("%1$tY-%1$tm-%1$td",modelDatePR.getValue());
		String dateRD = String.format("%1$tY-%1$tm-%1$td",modelDateRdv.getValue());
		Patient patient = (Patient) cbRecherche.getSelectedItem();
		Date datepr = Date.valueOf(datePR);
		Date daterd = Date.valueOf(dateRD);
		
		
		// Le premier controle : les champs vides ..

		//User user = DAOUser.getByID(1);
		RendezVous rendezVous = new RendezVous(null,datepr, daterd,
				observation,patient.getId(),null);
		DAORendezVous.insert(rendezVous);

	
		row[0] = rendezVous.getId();
		row[4] = rendezVous.getDatePR();
		row[5] = rendezVous.getDateRD();
		row[3] = rendezVous.getObservation();
		row[6] = patient.getId();
		row[1] = patient.getNom();
		row[2] = patient.getPrenom();

		model.addRow(row);
		viderChamps();
		JOptionPane.showMessageDialog(this, "RendezVous inséré ...");

	}

	
	
	private void bModifierclicked() {
		int row = table.getSelectedRow();
        
		if (row >=0) {
			long id = Long.parseLong(model.getValueAt(row, 0).toString());
			
			
			RendezVous p = DAORendezVous.getByID(id);
         	
			String observation = tObservation.getText().trim();
			String datePR= String.format("%1$tY-%1$tm-%1$td",modelDatePR.getValue());
			String dateRD = String.format("%1$tY-%1$tm-%1$td",modelDateRdv.getValue());
			Patient patient = (Patient) cbRecherche.getSelectedItem();
			Date datepr = Date.valueOf(datePR);
			Date daterd = Date.valueOf(dateRD);

			//User user = DAOUser.getByID(1);
			RendezVous rendezVous = new RendezVous(null,datepr, daterd,
					observation,patient.getId(),null);
			// La modification dans la base de données
			p.setRendezVousParameters(p.getId(),datepr, daterd,observation,patient.getId(),null);
			
			DAORendezVous.update(p);
			
			//not important
//			cbRecherche.removeItemAt(row);
//			cbRecherche.insertItemAt(p, row);
//			cbRecherche.setSelectedIndex(row);
		
		
	
			// La moification dans le Jtable
			model.setValueAt(p.getId(), row, 0);
			model.setValueAt(observation, row, 3);
			model.setValueAt(datepr, row, 4);
			model.setValueAt(daterd, row, 5);
			model.setValueAt(p.getIdPatient(),row, 6);
			model.setValueAt(DAOPatient.getByID(p.getIdPatient()).getNom(), row, 1);
			model.setValueAt(DAOPatient.getByID(p.getIdPatient()).getPrenom(), row,2);
		

			
				
			JOptionPane.showMessageDialog(this, "RendezVous modifié ...");
		} else {
			JOptionPane.showMessageDialog(this, "Veuillez selctionner une ligne s'il vous plait ...");
		}

	}

	private void bSupprimerclicked() {
		int row = table.getSelectedRow();
		if (row >= 0) {
			// si row est inferieur a zero alors aucune ligne n'a été sélectionnée
			long id = Long.parseLong(model.getValueAt(table.getSelectedRow(), 0).toString());
			RendezVous p = DAORendezVous.getByID(id);
			int yes = JOptionPane.showConfirmDialog(this, "Voulez vous supprimer ce rendez vous ?", "Supprimer",
					JOptionPane.YES_NO_OPTION);
			if (yes == JOptionPane.YES_OPTION) {
				
			//	cbRecherche.removeItemAt(table.getSelectedRow());
				// suppression dans la bdd
				DAORendezVous.delete(id);
				// suppression au jtable
				model.removeRow(row);
				viderChamps();
				
			
				
			}

		} else {
			JOptionPane.showMessageDialog(this, "Veuillez selctionner une ligne s'il vous plait ...");
		}
		
		
			

	}
	
	private void viderChamps() 
	{
		tObservation.setText("");
	    modelDatePR.setSelected(false);
	    modelDateRdv.setSelected(false);
		
	}
	
	public UIRendezvousPanel getuiPanel() {
		return uirendezvouspanel;
	}
	
	
	

}
