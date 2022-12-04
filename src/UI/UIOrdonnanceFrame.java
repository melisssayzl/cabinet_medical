package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modele.beans.Consultation;
import modele.beans.LigneOrdonnance;
import modele.beans.Patient;
import models.dao.DAOConsultation;
import models.dao.DAOLigneOrdonnance;
import ui.utils.Mylib;
import ui.utils.PDFutils;

public class UIOrdonnanceFrame extends JFrame {
	private static final long serialVersionUID = 1L;

//	Les attributs : composants internes ....
	private  JPanel pWrapper; 
	private JTextField tMedicament;
	private JTextField tObservation;
	private JSpinner spQuantite;
	private JSpinner spNbPriseJour;
	private JSpinner spNbJours;
	
	private JTable table;
	private List<LigneOrdonnance> ligneordo;
	private DefaultTableModel model;
	private Object[] row;
	private Object[] columns = { "ID", "Médicament", "Quantité", "Nb Prise/Jour", "Nb jours", "Observation", "ID consultation" };
	private JScrollPane scroll;
	
	
	
//	private Long id = null;
//	private Long tIdConsultation = null;/
//	private String medicament = "";
//	private Long quantite = null;
//	private Long nbPriseJour = null;
//	private Long nbJours = null;
//	private String observation = "";
	

	private JLabel lMedicament;
	private JLabel lObservation;
	private JLabel lQuantite;
	private JLabel lNbrePriseJours;
	private JLabel tNbjours;

	private JButton bAjouter;
	private JButton bModifier;
	private JButton bSupprimer;
	private JButton bPdf;

//	Les méthodes ....
	public UIOrdonnanceFrame() {
		initUI();
		layout1();
		handleEvents();
	}

	
	
	private void initUI() {
//		Instancier les composants internes et les paramétrer ...
		pWrapper = new JPanel();
		pWrapper.setBackground(Color.white);
		
		tMedicament = new JTextField();
		tObservation = new JTextField();
		spQuantite = new JSpinner();
		spNbPriseJour = new JSpinner();
		spNbJours = new JSpinner();
	
		

		lMedicament = Mylib.createLabel("Médicament : ");
		lObservation = Mylib.createLabel("Observation : ");
		lQuantite = Mylib.createLabel("Quantité : ");
		lNbrePriseJours = Mylib.createLabel("Nb Prise/Jour : ");
		tNbjours = Mylib.createLabel("Nb Jours : ");

		bAjouter = Mylib.createButton("Ajouter",null);
		bModifier = Mylib.createButton("Modifier",null);
		bSupprimer = Mylib.createButton("Supprimer",null);
		bPdf = Mylib.createButton("Imprimer",null);
		
		
     
		
		
		//Entrer pou passer a lautre tf
		Mylib.TextNext(tMedicament, tObservation);
		
		
		

		scroll = new JScrollPane();

        //Tous ce qui concerne l'instanciation du jtable
		table = new JTable();
		
		// les colonnes du jtable
		row = new Object[9];
		table.setBackground(new Color(240, 248, 236));
		//l'ajout du scroll au jtable
		scroll.setViewportView(table);
		
		// le modele du tableau
		model = new DefaultTableModel();
		// set the column on top of the table
		model.setColumnIdentifiers(columns);
		// set the model to the table
		table.setModel(model);
		
		ligneordo = DAOLigneOrdonnance.getList();
		// long id = 
		fillJtable(Long.parseLong(UIConsultationPanel.model.getValueAt(UIConsultationPanel.table.getSelectedRow(),0).toString()));		
       Mylib.createTable(table);
		
		// une fct qui place les informations du ligneOrdonnance dans le jtable
	//	fillJtable(listPatients);
		//une fct qui place les informations du ligneOrdonnance dans Combo Box
	//	fillCbox(listPatients);
		this.setContentPane(pWrapper);
		this.setSize(700,800);
        this.setLocationRelativeTo(null);
		this.setTitle("Ordonnance");
		//this.setMinimumSize(this.getSize());

		//Pour ne pas fermer la fenetre principale 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
  
      
		
		
	}

//	Gerer la dispoistion (layout management) : placer les composants internes sur le container ...


	
	
	
	
	
	
	
	public void layout1() {	
		pWrapper.removeAll();
		
		GroupLayout layout = new GroupLayout(pWrapper);
		pWrapper.setLayout(layout);
		
	
			// Spécifier la vue horizontalement ....
			layout.setHorizontalGroup(layout.createParallelGroup()
//					.addGap(20,20,Short.MAX_VALUE)
					.addGroup(layout.createSequentialGroup()
							.addGap(20,30,Short.MAX_VALUE)
							.addGroup(layout.createParallelGroup()

									.addGap(5)
									.addComponent(lMedicament)
									.addGap(5)
									.addComponent(lObservation)
									.addGap(5)
									.addComponent(lQuantite)
									.addGap(5)
									.addComponent(lNbrePriseJours)
									.addGap(5)
									.addComponent(tNbjours)
						    )
							
							.addGroup(layout.createParallelGroup()
									.addGap(5)
									.addComponent(tMedicament,120,200,200)										
									.addGap(5)
									.addComponent(tObservation,120,200,200)
									.addGap(5)
									.addComponent(spQuantite,50,50,50)
									
									.addGap(5)
									.addComponent(spNbPriseJour,50,50,50)
									.addGap(5)
									.addComponent(spNbJours,50,50,50)
									.addGap(5)

									
					
							)
							.addGap(20,30,Short.MAX_VALUE)
							)
					 
					
					.addGroup(layout.createSequentialGroup()
							.addGap(20,30,Short.MAX_VALUE)
							
							.addComponent(bAjouter,100,100,100)
							.addGap(20,30,40)
							.addComponent(bSupprimer,100,100,100)
							.addGap(20,30,40)
							.addComponent(bModifier,100,100,100)
							.addGap(20,30,40)
							.addComponent(bPdf,100,100,100)

							.addGap(20,30,Short.MAX_VALUE)
							
							
					)
					.addGroup(layout.createSequentialGroup()
							.addGap(20,30,Short.MAX_VALUE)
							.addComponent(scroll,350,400,600)
							.addGap(20,30,Short.MAX_VALUE)
					)		
							
							

			);

			// Spécifier la vue verticalement ....
			layout.setVerticalGroup(layout.createSequentialGroup()
					                .addGap(25,30,Short.MAX_VALUE)
					                
									.addGroup(layout.createParallelGroup()
											.addComponent(lMedicament)
											.addComponent(tMedicament, 40,40,50))
									.addGap(17,17,20)
									.addGroup(layout.createParallelGroup()
											.addComponent(lObservation)
											.addComponent(tObservation,40,40,50)

									)
									.addGap(17,17,20)
									.addGroup(layout.createParallelGroup()
											.addComponent(lQuantite)
											.addComponent(spQuantite, 40,40,50)

									).addGap(17,17,20)
									
									.addGroup(layout.createParallelGroup()
											.addComponent(lNbrePriseJours)
											.addComponent(spNbPriseJour,40,40,50)

									).addGap(17,17,20)
									.addGroup(layout.createParallelGroup()
											.addComponent(tNbjours)
											.addComponent(spNbJours,40,40,50)

									)
					             
									.addGroup(layout.createParallelGroup()
											//.addComponent(tRecherche,30,50,50)
											.addGap(30)
											.addComponent(bAjouter,63,63,63)
											.addGap(30)
											.addComponent(bSupprimer,63,63,63)
											.addGap(30)
											.addComponent(bModifier,63,63,63)	
											.addGap(30)
											.addComponent(bPdf,63,63,63)	
									)
									.addGap(10,25,35)
									.addComponent(scroll,200,300,300)
									
									.addGap(25,30,Short.MAX_VALUE)

				
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

		tMedicament.addFocusListener(fl);
		tObservation.addFocusListener(fl);
		spQuantite.addFocusListener(fl);
		spNbPriseJour.addFocusListener(fl);
		spNbJours.addFocusListener(fl);

		// Les actions des boutons
	
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
		
		
		bPdf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				   //recuperartion de l'id de la consultation
				   long id = Long.parseLong(UIConsultationPanel.model.getValueAt(UIConsultationPanel.table.getSelectedRow(),0).toString()) ;
				   Consultation c = DAOConsultation.getByID(id);
				   Patient p = UIConsultationPanel.listItems.getSelectedValue();
				   LigneOrdonnance o = DAOLigneOrdonnance.getByID(id);
				   try {
						PDFutils.createPDF(o);
						
				   }catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				   }
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fillTextFields();

			}
		});
		
	
		
	}


	

	private void fillTextFields() {
		int row = table.getSelectedRow();
		tMedicament.setText(model.getValueAt(row, 1).toString());
		spQuantite.setValue(Long.parseLong(model.getValueAt(row, 2).toString()));
		spNbPriseJour.setValue(Long.parseLong(model.getValueAt(row, 3).toString()));
		spNbJours.setValue(Long.parseLong(model.getValueAt(row, 4).toString()));
		tObservation.setText(model.getValueAt(row, 5).toString());
	
    }



	//	Les méthodes assiciées aux listeners ...

	private void fillJtable(long idConsultation) {
		ligneordo = DAOLigneOrdonnance.getListByIDconsu(idConsultation);
		Iterator<LigneOrdonnance> it = ligneordo.iterator();

		while (it.hasNext()) {
			LigneOrdonnance o = (LigneOrdonnance) it.next();
			row[0] = o.getId();
			row[1] = o.getMedicament();
			row[2] = o.getQuantite();
			row[3] = o.getNbPriseJour();
			row[4] = o.getNbJours();
			row[5] = o.getObservation();
			row[6] = o.getIdConsultation();
			
			model.addRow(row);
			
		}
	}
	
	private void bAjouterClicked() {

		
		String medicament = tMedicament.getText().trim();
		String quantite = spQuantite.getValue().toString();
		String nbPriseJour = spNbPriseJour.getValue().toString();
		String nbJours = spNbJours.getValue().toString();
		String observation = tObservation.getText().trim();
		String idConsultaton = UIConsultationPanel.model.getValueAt(UIConsultationPanel.table.getSelectedRow(),0).toString();
	
		// Le premier controle : les champs vides ..
		LigneOrdonnance ligneOrdonnance =  new LigneOrdonnance(null,Long.parseLong(idConsultaton),medicament,Long.parseLong(quantite),
				Long.parseLong(nbPriseJour),Long.parseLong(nbJours),observation);
		DAOLigneOrdonnance.insert(ligneOrdonnance);
				

		row[0] = ligneOrdonnance.getId();
		row[1] = ligneOrdonnance.getMedicament();
		row[2] = ligneOrdonnance.getQuantite();
		row[3] = ligneOrdonnance.getNbPriseJour();
		row[4] = ligneOrdonnance.getNbJours();
		row[5] = ligneOrdonnance.getObservation();
		row[6] = ligneOrdonnance.getIdConsultation();
		
		model.addRow(row);
     	
		viderChamps();
		JOptionPane.showMessageDialog(this, "Ligne d'Ordonnance insérée ...");

	}

	private void bModifierclicked() {
		int row = table.getSelectedRow();
        
		if (row >=0) {
			long id = Long.parseLong(model.getValueAt(row, 0).toString());
			
			LigneOrdonnance o = DAOLigneOrdonnance.getByID(id);
         	
			String medicament = tMedicament.getText().trim();
			String quantite = spQuantite.getValue().toString();
			String nbPriseJour = spNbPriseJour.getValue().toString();
			String nbJours = spNbJours.getValue().toString();
			String observation = tObservation.getText().trim();
			String idConsultaton = UIConsultationPanel.model.getValueAt(UIConsultationPanel.table.getSelectedRow(),0).toString();
		
			o.setMedicament(medicament);
			o.setQuantite(Long.parseLong(quantite));
			o.setNbJours(Long.parseLong(nbJours));
			o.setNbPriseJour(Long.parseLong(nbPriseJour));
			o.setObservation(observation);
			o.setIdConsultation(Long.parseLong(idConsultaton));
	        DAOLigneOrdonnance.update(o);
			

			// La moification dans le Jtable
			model.setValueAt(o.getId(), row, 0);
			model.setValueAt(medicament, row, 1);
			model.setValueAt(quantite, row, 2);
			model.setValueAt(nbPriseJour, row, 3);
			model.setValueAt(nbJours, row, 4);
			model.setValueAt(observation, row, 5);
			model.setValueAt(o.getIdConsultation(), row, 6);
			
				
			JOptionPane.showMessageDialog(this, "Ligne d'Ordonnance modifié ...");
		} else {
			JOptionPane.showMessageDialog(this, "Veuillez selctionner une ligne s'il vous plait ...");
		}

	}

	private void bSupprimerclicked() {
		int row = table.getSelectedRow();
		if (row >= 0) {
			// si row est inferieur a zero alors aucune ligne n'a été sélectionnée
			long id = Long.parseLong(model.getValueAt(table.getSelectedRow(), 0).toString());
			int yes = JOptionPane.showConfirmDialog(this, "Voulez vous supprimer ce ligneOrdonnance", "Supprimer",
					JOptionPane.YES_NO_OPTION);
			if (yes == JOptionPane.YES_OPTION) {

				DAOLigneOrdonnance.delete(id);
			    model.removeRow(row);
				viderChamps();
				
			}
				
			

		} else {
			JOptionPane.showMessageDialog(this, "Veuillez selctionner une ligne s'il vous plait ...");
		}

}
	
	
	private void viderChamps() 
	{
		tMedicament.setText("");
	    spQuantite.setValue(0);
		tObservation.setText("");
		spNbPriseJour.setValue(0);
		spNbJours.setValue(0);
		
	}
	


}
