package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import modele.beans.Patient;
import models.dao.DAOConsultation;
import models.dao.DAOPatient;
import models.dao.DAORendezVous;
import ui.utils.IconConstants;
import ui.utils.Mylib;

public class UIPatientPanel extends JPanel {
	private static final long serialVersionUID = 1L;

//	Les attributs : composants internes ....
	private JTextField tNom;
	private JTextField tPrenom;
	private JTextField tAdresse;
	private JTextField tAge;
	private JComboBox<String> cbGS;
	private JTextField tTel;
	private JTextField tTaille;
	private JTextField tPoids;
	

	
	public String index="";
	public JTextField azul;
	private JTable table;
	private JTextField tRecherche;
	private List<Patient> listPatients;
	private DefaultTableModel model;
	private Object[] row;
	private Object[] columns = { "id", "nom", "prenom", "adresse", "age", "gs", "tel", "taille", "poids" };
	private JScrollPane scroll;
	

	private JLabel lPrenom;
	private JLabel lNom;
	private JLabel lAdresse;
	private JLabel lAge;
	private JLabel lGs;
	private JLabel lTel;
	private JLabel lTaille;
	private JLabel lPoids;
	private JButton bAjouter;
	private JButton bModifier;
	private JButton bSupprimer;
	private JComboBox<Patient> cbRecherche;

//	Les méthodes ....
	public UIPatientPanel() {
		initUI();
		handleEvents();
	}

	
	
	private void initUI() {
//		Instancier les composants internes et les paramétrer ...
		tNom = new JTextField();
		tPrenom = new JTextField();
		tAdresse = new JTextField();
		tAge = new JTextField();
		cbGS = new JComboBox<String>();
		tTel = new JTextField();
		tTaille = new JTextField();
		tPoids = new JTextField();
		tRecherche = new JTextField() ;
		azul = new JTextField();
		

		lNom = Mylib.createLabel("Nom : ");
		lPrenom = Mylib.createLabel("Prénom : ");
		lAdresse = Mylib.createLabel("Adresse : ");
		lAge = Mylib.createLabel("Age : ");
		lGs = Mylib.createLabel("Groupe sanguin :");
		lTel = Mylib.createLabel("Téléphone : ");
		lTaille = Mylib.createLabel("Taille : ");
		lPoids = Mylib.createLabel("Poids : ");

		bAjouter = Mylib.createButton("",IconConstants.AJOUTER_PATIENT_50_50);
		bModifier = Mylib.createButton("", IconConstants.MODIFIER_PATIENT_50_50);
		bSupprimer = Mylib.createButton("",IconConstants.SUPPRIMER_PATIENT_50_50 );
		
		cbRecherche = new JComboBox<Patient>();
		
		
     
		scroll = new JScrollPane();
		
		
		//Entrer pou passer a lautre tf
		Mylib.TextNext(tNom, tPrenom);
		Mylib.TextNext(tPrenom, tAdresse);
		Mylib.TextNext(tAdresse, tAge);
		Mylib.TextNext(tAge,tTel);
		Mylib.TextNext(tTel, tTaille);
		Mylib.TextNext(tTaille, tPoids);
		
		

		// Ajout des lements au combo box : remplissage de cbGS
		cbGS.addItem("A+");
		cbGS.addItem("A-");
		cbGS.addItem("B+");
		cbGS.addItem("B-");
		cbGS.addItem("AB+");		
		cbGS.addItem("AB-");
		cbGS.addItem("O+");
		cbGS.addItem("O-");

        //Recuperation de la liste des patients
		listPatients = DAOPatient.getList();

		
       
		
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
		
		// une fct qui place les informations du patient dans le jtable
		fillJtable(listPatients);
		//une fct qui place les informations du patient dans Combo Box
		fillCbox(listPatients);
		
	
		Mylib.createTable(table);
		
	
		
  
        this.setSize(500,400);
		
		
	}

//	Gerer la dispoistion (layout management) : placer les composants internes sur le container ...


	
	public void layout1() {	
		this.removeAll();
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		// Spécifier la vue horizontalement ....
				layout.setHorizontalGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								
								.addGap(40,50,Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup()
      
										.addComponent(lNom)
										.addGap(5)
										.addComponent(lPrenom)
										.addGap(5)
										.addComponent(lAdresse)
										.addGap(5)
										.addComponent(lAge)
										.addGap(5)
										.addComponent(lGs)
										.addGap(5)
										.addComponent(lTel)
										.addGap(5)
										.addComponent(lTaille)
										.addGap(5)
										.addComponent(lPoids))
								.addGap(15,30,40)
								.addGroup(layout.createParallelGroup()
										.addComponent(tNom,100,200,200)
										.addGap(5)
										.addComponent(tPrenom,100,200,200)										
										.addGap(5)
										.addComponent(tAdresse,100,200,200)
										.addGap(5)
										.addComponent(tAge,100,200,200)
										.addGap(5)
										.addComponent(cbGS,100,200,200)
										.addGap(5)
										.addComponent(tTel,100,200,200)
										.addGap(5)
										.addComponent(tTaille,100,200,200)
										.addGap(5)
										.addComponent(tPoids, 100,200,200)
										

								)
								.addGap(40,100,120)
								.addGroup(layout.createParallelGroup()
										
										.addComponent(scroll,300,600,750)
										.addGap(30)
										.addGroup(layout.createSequentialGroup()
						                         .addGap(0,100,Short.MAX_VALUE)
												.addComponent(tRecherche,200,250,300)
												.addGap(10,70,80)
											    .addComponent(bAjouter, 62,62,62)
												.addGap(10,10,20)
												.addComponent(bSupprimer, 62,62,62)
												.addGap(10,10,20)
												.addComponent(bModifier,62,62,62)
												.addGap(0,100,Short.MAX_VALUE)
												
										)
										
								)
									
								.addGap(40,50,Short.MAX_VALUE)
								)
				

				);

				layout.setVerticalGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
						.addGap(20,40,Short.MAX_VALUE)
						
		                .addGroup(layout.createParallelGroup()
		                		.addGroup(layout.createParallelGroup()
		                				.addGap(3)
		                				.addComponent(tRecherche,55,55,55)
		                				.addGap(3)
		                		)
		                		
				                .addGap(30,30,30)
								.addComponent(bAjouter,62,62,62)
								.addGap(50, 50, 80)
								.addComponent(bModifier,62,62,62)
								.addGap(50, 50, 80)
								.addComponent(bSupprimer,62,62,62)

						)
		                .addGap(20,20,20)
						.addGroup(layout.createParallelGroup()
		                                 
								.addComponent(scroll,250,250,250)
								.addGroup(layout.createSequentialGroup()

										.addGroup(
												layout.createParallelGroup()
												.addComponent(lNom)
												.addComponent(tNom, 20,30,30)
												
												)
										.addGap(5, 10, 10)
										
										.addGroup(layout.createParallelGroup()
												.addComponent(lPrenom)
												.addComponent(tPrenom, 20,30,30))
										.addGap(10, 10, 10)
										.addGroup(layout.createParallelGroup()
												.addComponent(lAdresse)
												.addComponent(tAdresse,20,30,30)

										)
										.addGap(5, 10, 10)
										.addGroup(layout.createParallelGroup()
												.addComponent(lAge)
												.addComponent(tAge, 20,30,30)

										).addGap(5, 10, 10)
										.addGroup(layout.createParallelGroup()
												.addComponent(lGs)
												.addComponent(cbGS, 15,30,30)

										).addGap(5, 10, 10)
										.addGroup(layout.createParallelGroup()
												.addComponent(lTel)
												.addComponent(tTel,  20,30,30)

										).addGap(5, 10, 10)
										.addGroup(layout.createParallelGroup()
												.addComponent(lTaille)
												.addComponent(tTaille, 20,30,30)

										).addGap(5, 10, 10)
										.addGroup(layout.createParallelGroup()
												.addComponent(lPoids)
												.addComponent(tPoids, 20,30,30)

										)
										
							
										.addGap(100, 100, Short.MAX_VALUE)

								)
								.addGap(20,40,Short.MAX_VALUE)
							
								)

				)

				);

		

	}

	public void layout2() {
	    this.removeAll();
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		// Spécifier la vue horizontalement ....
		layout.setHorizontalGroup(layout.createParallelGroup()
//				.addGap(20,20,Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addGap(20,30,Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup()

								.addComponent(lNom)
								.addGap(5)
								.addComponent(lPrenom)
								.addGap(5)
								.addComponent(lAdresse)
								.addGap(5)
								.addComponent(lAge)
								.addGap(5)
								.addComponent(lGs)
								.addGap(5)
								.addComponent(lTel)
								.addGap(5)
								.addComponent(lTaille)
								.addGap(5)
								.addComponent(lPoids))
						.addGap(5)
						.addGroup(layout.createParallelGroup()
								.addComponent(tNom,125,150,250)
								.addGap(5)
								.addComponent(tPrenom,125,150,250)
								.addGap(5)
								.addComponent(tAdresse,125,150,250)
								.addGap(5)
								.addComponent(tAge,125,150,250)
								.addGap(5)
								.addComponent(cbGS,125,150,250)
								.addGap(5)
								.addComponent(tTel,125,150,250)
								.addGap(5)
								.addComponent(tTaille,125,150,250)
								.addGap(5)
								.addComponent(tPoids,125,150,250)
								
				
						)
						.addGap(20,30,Short.MAX_VALUE)
						)
				 
				
				.addGroup(layout.createSequentialGroup()
						.addGap(20,30,Short.MAX_VALUE)
						.addComponent(tRecherche,200,250,400)
						.addGap(20,30,40)
						.addComponent(bAjouter,63,63,63)
						.addGap(20,30,40)
						.addComponent(bSupprimer,63,63,63)
						.addGap(20,30,40)
						.addComponent(bModifier,63,63,63)
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
								.addGroup(
										layout.createParallelGroup()
										.addComponent(lNom)
										.addComponent(tNom,20,30,30)
										
										)
								.addGap(10,10,20)
								
								.addGroup(layout.createParallelGroup()
										.addComponent(lPrenom)
										.addComponent(tPrenom, 20,30,30))
								.addGap(10,10,20)
								.addGroup(layout.createParallelGroup()
										.addComponent(lAdresse)
										.addComponent(tAdresse, 20,30,30)

								)
								.addGap(10,10,20)
								.addGroup(layout.createParallelGroup()
										.addComponent(lAge)
										.addComponent(tAge,20,30,30)

								).addGap(10,10,20)
								.addGroup(layout.createParallelGroup()
										.addComponent(lGs)
										.addComponent(cbGS,20,30,30)

								).addGap(10,10,20)
								.addGroup(layout.createParallelGroup()
										.addComponent(lTel)
										.addComponent(tTel,20,30,30)

								).addGap(10,10,20)
								.addGroup(layout.createParallelGroup()
										.addComponent(lTaille)
										.addComponent(tTaille,20,30,30)

								).addGap(10,10,20)
								.addGroup(layout.createParallelGroup()
										.addComponent(lPoids)
										.addComponent(tPoids,20,30,30)

								)
								.addGap(10,15,20)
								
								.addGroup(layout.createParallelGroup()
										.addComponent(tRecherche,30,50,50)
										.addGap(30).addComponent(bAjouter,63,63,63)
										.addGap(30)
										.addComponent(bSupprimer,63,63,63)
										.addGap(30)
										.addComponent(bModifier,63,63,63)	
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

		tPrenom.addFocusListener(fl);
		tNom.addFocusListener(fl);
		tAdresse.addFocusListener(fl);
		tAge.addFocusListener(fl);
		cbGS.addFocusListener(fl);
		tTel.addFocusListener(fl);
		tTaille.addFocusListener(fl);
		tPoids.addFocusListener(fl);

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
		
		 //affecture la recherche avec u text field

		
		//key pressed : au fure a mesure qu'on tape au clavier
		tRecherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    
				recherche();
			}
		});
		
		
		
		//Delete all rows of Jtable 
		//cbRecherche.getSelectedObjects()
//		 model.getDataVector().removeAllElements();
//		 
//		 List<Patient> list2 = DAOPatient.getList3("ami");
//		 System.out.println(list2);
//		 fillJtable(list2);
		
		
		
		//combo selection changed
//	    cbRecherche.addItemListener(new ItemListener() {
//	    	
//			
//			@Override
//			public void itemStateChanged(ItemEvent e) {
// 					
//						String query = cbRecherche.getSelectedItem().toString();
//						filter(query);
//				
//			}
//			
//		
//		});
		
//		bRecherche.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				String str = tRecherche.getText().trim();
//				 model.getDataVector().removeAllElements();
//				 List<Patient> list2 = DAOPatient.getList3(str);
//				 System.out.println(list2);
//				 fillJtable(list2);
//				
//			}
//		});
	    
		
		// remplir les champs du tf avec l'element sélectionné du tableau

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fillTextFields();

			}
		});
		
		
		bAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColor(bAjouter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(bAjouter);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		bModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColor(bModifier);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(bModifier);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		bSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColor(bSupprimer);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(bSupprimer);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	
	private void setColor(JButton btn) {
		btn.setBackground(new Color(153, 194, 255));
	}

    private void resetColor(JButton btn) {
    	btn.setBackground(new Color(102, 179, 255));
	}

	
	private void recherche() {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(tRecherche.getText().trim()));
		
	}

	private void fillTextFields() {
		int row = table.getSelectedRow();// la ligne sélectionnée
		tNom.setText(model.getValueAt(row, 1).toString());
		tPrenom.setText(model.getValueAt(row, 2).toString());
		tAdresse.setText(model.getValueAt(row, 3).toString());
		tAge.setText(model.getValueAt(row, 4).toString());
		cbGS.setSelectedItem(model.getValueAt(row, 5).toString());
		tTel.setText(model.getValueAt(row, 6).toString());
		tTaille.setText(model.getValueAt(row, 7).toString());
		tPoids.setText(model.getValueAt(row, 8).toString());

	}

	
//	Les méthodes assiciées aux listeners ...
	
	private void fillJtable(List<Patient> listeQQ) {
		// Iterator<Animal> it = zoocop.iterator();

		// while ( it.hasNext() ){
		// Animal o = it.next();
		// System.out.println(o.getNom()+" "+o.getAge());

		Iterator<Patient> it = listeQQ.iterator();
		while (it.hasNext()) {
			Patient p = (Patient) it.next();
			row[0] = p.getId();
			row[1] = p.getNom();
			row[2] = p.getPrenom();
			row[3] = p.getAdresse();
			row[4] = p.getAge();
			row[5] = p.getGs();
			row[6] = p.getTel();
			row[7] = p.getTaille();
			row[8] = p.getPoids();
			model.addRow(row);
			
		}
	}
	
	
	private void fillCbox(List<Patient> listQQ)
	{

		Iterator<Patient> it = listQQ.iterator();
		while (it.hasNext()) {
			Patient p = (Patient) it.next();
			cbRecherche.addItem(p);
		}
		
	}

	private void bAjouterClicked() {

		String nom = tNom.getText().trim();
		String prenom = tPrenom.getText().trim();
		String adresse = tAdresse.getText().trim();
		String age = tAge.getText().trim();
		String gs = (String) cbGS.getSelectedItem();
		String tel = tTel.getText().trim();
		String taille = tTaille.getText();
		String poids = tPoids.getText().trim();
		// Le premier controle : les champs vides ..

		if (nom.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'Nom' ...", "Erreur : Champs vide ...",
					JOptionPane.ERROR_MESSAGE);
			tNom.requestFocus();// activer le champs code ...
			return;
		}

		if (prenom.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'Prénom' ...", "Erreur : Champs vide ...",
					JOptionPane.ERROR_MESSAGE);
			tPrenom.requestFocus();// activer le champs code ...
			return;
		}
		if (adresse.equals("")) {
			// JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'code' ...");
			JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'Adresse' ...", "Erreur : Champs vide ...",
					JOptionPane.ERROR_MESSAGE);
			tAdresse.requestFocus();// activer le champs code ...
			return;
		}
		if (age.equals("")) {
			// JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'code' ...");
			JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'Age' ...", "Erreur : Champs vide ...",
					JOptionPane.ERROR_MESSAGE);
			tAge.requestFocus();// activer le champs code ...
			return;
		}

		if (gs.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'Groupe sanguin' ...",
					"Erreur : Champs vide ...", JOptionPane.ERROR_MESSAGE);
			cbGS.requestFocus();// activer le champs code ...
			return;
		}

		if (tel.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'Numéro de téléphone' ...",
					"Erreur : Champs vide ...", JOptionPane.ERROR_MESSAGE);
			tTel.requestFocus();// activer le champs code ...
			return;
		}
		if (taille.equals("")) {
			// JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'code' ...");
			JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'Taille' ...", "Erreur : Champs vide ...",
					JOptionPane.ERROR_MESSAGE);
			tTaille.requestFocus();// activer le champs code ...
			return;
		}

		if (poids.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'Poids' ...", "Erreur : Champs vide ...",
					JOptionPane.ERROR_MESSAGE);
			tPoids.requestFocus();// activer le champs code ...
			return;
		}

		Patient patient = new Patient(null, nom, prenom, adresse, Long.parseLong(age), gs, tel, Long.parseLong(taille),
				Long.parseLong(poids));
		DAOPatient.insert(patient);
	    UIConsultationPanel.dlmListItems.addElement(patient);
		UIRendezvousPanel.cbRecherche.addItem(patient);

		row[0] = patient.getId();
		row[1] = patient.getNom();
		row[2] = patient.getPrenom();
		row[3] = patient.getAdresse();
		row[4] = patient.getAge();
		row[5] = patient.getGs();
		row[6] = patient.getTel();
		row[7] = patient.getTaille();
		row[8] = patient.getPoids();
		model.addRow(row);
     	
		viderChamps();
		JOptionPane.showMessageDialog(this, "Patient inséré ...");

	}

	private void bModifierclicked() {
		int row = table.getSelectedRow();
        
		if (row >=0) {
			long id = Long.parseLong(model.getValueAt(row, 0).toString());
			
			
			Patient p = DAOPatient.getByID(id);
         	
			String nom = tNom.getText().trim();
			String prenom = tPrenom.getText().trim();
			String adresse = tAdresse.getText().trim();
			String age = tAge.getText().trim();
			String gs = (String) cbGS.getSelectedItem();
			String tel = tTel.getText().trim();
			String taille = tTaille.getText();
			String poids = tPoids.getText().trim();

			// La modification dans la base de données
			p.setPatientParameters(p.getId(), nom, prenom, adresse, Long.parseLong(age), gs, tel,
					Long.parseLong(taille), Long.parseLong(poids));
			
			DAOPatient.update(p);
			//lamodification dans la liste des consultations			
			UIConsultationPanel.dlmListItems.setElementAt(p, row);
			
			//modification dans cb des patients dans rdv panel
			UIRendezvousPanel.cbRecherche.removeItemAt(row);
			UIRendezvousPanel.cbRecherche.insertItemAt(p, row);
			UIRendezvousPanel.cbRecherche.setSelectedIndex(row);
			
			
			
			//modif dans jtable des patients dans rdv panel
		    int i =0;
			
		     while(i<UIRendezvousPanel.table.getRowCount()) {
		    	
		    	 if(id == Long.parseLong( UIRendezvousPanel.model.getValueAt(i,6).toString())){
	    			UIRendezvousPanel.model.setValueAt(nom,i, 1);
	    			UIRendezvousPanel.model.setValueAt(prenom,i,2);
	    			System.out.println("ok");
		    	 }
		    	 i++;
		    	
		    	 
		     }
			
			

			// La moification dans le Jtable
			model.setValueAt(p.getId(), row, 0);
			model.setValueAt(nom, row, 1);
			model.setValueAt(prenom, row, 2);
			model.setValueAt(adresse, row, 3);
			model.setValueAt(age, row, 4);
			model.setValueAt(gs, row, 5);
			model.setValueAt(tel, row, 6);
			model.setValueAt(taille, row, 7);
			model.setValueAt(poids, row, 8);
			
			
			
			
				
			JOptionPane.showMessageDialog(this, "Patient modifié ...");
		} else {
			JOptionPane.showMessageDialog(this, "Veuillez selctionner une ligne s'il vous plait ...");
		}

	}

	private void bSupprimerclicked() {
		int row = table.getSelectedRow();
		if (row >= 0) {
			// si row est inferieur a zero alors aucune ligne n'a été sélectionnée
			long id = Long.parseLong(model.getValueAt(table.getSelectedRow(), 0).toString());
			Patient p = DAOPatient.getByID(id);
			int yes = JOptionPane.showConfirmDialog(this, "Voulez vous supprimer ce patient", "Supprimer",
					JOptionPane.YES_NO_OPTION);
			if (yes == JOptionPane.YES_OPTION) {
			
				UIRendezvousPanel.cbRecherche.removeItemAt(table.getSelectedRow());
				DAORendezVous.deleteIDpatient(id);
	            DAOConsultation.deleteIDPatient(id);
				DAOPatient.delete(id);
				 
			 UIConsultationPanel.dlmListItems.removeElementAt(table.getSelectedRow());
               
			
				model.removeRow(row);
				int i =0;
				
			     while(i<UIRendezvousPanel.table.getRowCount()) {
			    	 
			    	 if(id == Long.parseLong( UIRendezvousPanel.model.getValueAt(i,6).toString())){
			    		 UIRendezvousPanel.model.removeRow(i);
			    	 }else {
			    		 i++;
			    	 }
			    	
			    	 
			     }
				
				
				
				
				viderChamps();
				
			}
				
			

		} else {
			JOptionPane.showMessageDialog(this, "Veuillez selctionner une ligne s'il vous plait ...");
		}

}
	
	private void viderChamps() 
	{
		tNom.setText("");
	    tPrenom.setText("");
		tAdresse.setText("");
		tAge.setText("");
		cbGS.setSelectedItem(cbGS.getItemAt(1));
		tTel.setText("");
		tTaille.setText("");
		tPoids.setText("");
		
	}
	


}
