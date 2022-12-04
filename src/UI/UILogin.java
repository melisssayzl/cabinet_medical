package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import modele.beans.User;
import ui.utils.IconConstants;
import ui.utils.Mylib;
import utils.userUTILS;

public class UILogin extends JFrame {
	private static final long serialVersionUID = 1L;

//	Les attributs : composants internes ....
	private JPanel pIntern;
	private JPanel pLeft;
	private JSeparator separateur1;
	private JSeparator separateur2;

	private JTextField tUsername;
	private JPasswordField tPassword;
	private JLabel lUsername;
	private JLabel lPassword;
	private JLabel lTitre;
	private JLabel lWelcome;
	private JLabel lForgotPass;
	private JLabel lExit;
	private JLabel lDisable;
	private JLabel iconLogin;

	private JButton bValider;
	private static UILogin singlton = null;
	private JCheckBox CbShowPassword;

	public static UILogin getSinglton() {
		if (singlton == null) {
			singlton = new UILogin();
		}

		return singlton;
	}
	

//	Les méthodes ....
	private UILogin() {
		this.initUI();
		this.layoutUI();
		this.handleEvents();
	}

	private void initUI() {
//		Instancier les composants internes et les paramétrer ...
		pIntern = new JPanel() {
	        private static final long serialVersionUID = 1L;

			@Override
	        protected void paintComponent(Graphics grphcs) {
	            super.paintComponent(grphcs);
	            Graphics2D g2 = (Graphics2D) grphcs;
	            g2.setPaint(Color.white);
	            Color color1= new Color(153, 51, 255);
	            Color color2= new Color(204, 153, 255);
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

		pLeft = new JPanel();
		pLeft.setBackground(new Color(217, 179, 255));
		
		CbShowPassword = new JCheckBox();
		CbShowPassword.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
		CbShowPassword.setForeground(new Color(199, 226, 255));
		CbShowPassword.setText("Remember Password");
		CbShowPassword.setOpaque(false);
		CbShowPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

		
		tUsername = Mylib.createTextField();
		tUsername.setOpaque(false);
		
		tUsername.setForeground(new Color(255, 255, 255));
		tUsername.setBorder(null);
		tUsername.setFont(new Font("Monospaced", 0,20)); // NOI18N
		tUsername.setCaretColor(new Color(255, 255, 255));

		iconLogin = Mylib.createLabelIconSimple("",IconConstants.LOGINB);
		
		lDisable = Mylib.createLabelIconSimple("", IconConstants.DISABLE_25_25);
		lDisable.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lDisable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				disableMouseClicked(evt);
			}
		});

		tPassword = new JPasswordField();
		tPassword.setOpaque(false);
		//tPassword.setFont(tPassword.getFont().deriveFont(tPassword.getFont().getSize() + 2f));
		tPassword.setFont(new Font("Monospaced",0,20));
		tPassword.setForeground(new Color(255, 255, 255));
		tPassword.setBorder(null);
		tPassword.setCaretColor(new Color(255, 255, 255));

		separateur1 = new JSeparator();
		separateur1.setForeground(new Color(255, 255, 255));

		separateur2 = new JSeparator();
		separateur2.setForeground(new Color(255, 255, 255));

		lExit = new JLabel();
		lExit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		lExit.setForeground(new java.awt.Color(255, 255, 255));
		lExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lExit.setText("X");
		lExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lExit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lExitMouseClicked(evt);
			}
		});

		lPassword = Mylib.createLabelIconSimple("Password", null);
		lPassword.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16)); // NOI18N
		lPassword.setForeground(new Color(238, 204, 255));

		lUsername = Mylib.createLabelIconSimple("Username", null);
		lUsername.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16)); // NOI18N
		lUsername.setForeground(new Color(238, 204, 255));


		lForgotPass = new JLabel("Mot de passe oublié ?");
		lForgotPass.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
		lForgotPass.setForeground(new Color(199, 226, 255));
		lForgotPass.setText("Forget Password?");
		lForgotPass.setCursor(new Cursor(Cursor.HAND_CURSOR));

		
		
		
		lTitre = Mylib.createLabel("  Login");
		lTitre.setFont(new Font("Segoe UI",Font.BOLD, 35)); // NOI18N
		lTitre.setForeground(new Color(199, 226, 255));
		
		lWelcome = Mylib.createLabel("Hello Let's get started ! ");
		lWelcome.setFont(new Font("Segoe UI",Font.ITALIC,22)); // NOI18N
		lWelcome.setForeground(new Color(199, 226, 255));
		
		

		bValider = new JButton("Valider");
		bValider.setBackground(new Color(255, 255, 255));
		bValider.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
		bValider.setForeground(new Color(25, 118, 211));
		bValider.setText("LOGIN");
		bValider.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));

//		Parametrer la fenetre ....
		this.setLocation(50, 150);
		// width = 450
		this.setSize(500, 600);

		this.setTitle("Authentification");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				
//		try {
//			//Utiliser le font comme ressource (dans le src)
//			
//			//importation du font qu'on veut appliquer sur le titre
//			Font font = Font.createFont(
//				Font.TRUETYPE_FONT, 
//				UILogin.class.getResourceAsStream( "/resources/fonts/Allura-Regular.otf" )
//			);
//			
//			
//			font  = font.deriveFont(Font.BOLD, 30f);
//
//			lTitre.setFont(font);
//			
//			tUsername.setFont(tUsername.getFont().deriveFont(18f));
//			tPassword.setFont( tPassword.getFont().deriveFont(20f) );
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		// ajouter une icone au boutton
		this.setContentPane(this.getContentPane());
		this.setSize(1380, 700);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(this.getSize());
	}

//	Gerer la dispoistion (layout management) : placer les composants internes sur le container ...
	private void layoutUI() {

		layout1();

	}

	private void layout1() {

		pIntern.removeAll();

		GroupLayout layout = new GroupLayout(pIntern);
		pIntern.setLayout(layout);
		// Spécifier la vue horizontalement ....
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addGap(200,200, Short.MAX_VALUE)
						.addComponent(lTitre, 200, 300, 300)
						.addGap(10, 10, Short.MAX_VALUE))
				
				.addGroup(layout.createSequentialGroup()
						.addGap(130,130, Short.MAX_VALUE)
						.addComponent(lWelcome, 200, 300, 300)
						.addGap(10, 10, Short.MAX_VALUE))

				.addGroup(layout.createSequentialGroup().addGap(10, 20, 30)
						.addGroup(layout.createParallelGroup().addComponent(lUsername)

								.addComponent(lPassword)

								.addComponent(tUsername, 100, 200, Short.MAX_VALUE)
								.addComponent(separateur1, 100, 200, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(tPassword, 100, 200, Short.MAX_VALUE).addComponent(lDisable))

								.addComponent(separateur2, 100, 200, Short.MAX_VALUE))

						.addGap(10, 20, 30))

				.addGroup(layout.createSequentialGroup().addGap(10, 20, 20)
						.addComponent(CbShowPassword, 100, 300, Short.MAX_VALUE)
						.addGap(10)
						.addGap(150, 200, Short.MAX_VALUE).addComponent(lForgotPass, 100, 300, Short.MAX_VALUE)
						.addGap(10, 20, Short.MAX_VALUE)

				)

				.addGroup(layout.createSequentialGroup()

						.addGap(10, 10, 20).addComponent(bValider, 100, 100, Short.MAX_VALUE).addGap(10, 10, 20))

		);

		// Spécifier la vue verticalement ....
		layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createSequentialGroup()
				.addGap(50, 75, 100)
				.addComponent(lTitre)
				.addGap(10,12,15)
				.addComponent(lWelcome)
				.addGap(5, 20, Short.MAX_VALUE)
				.addComponent(lUsername).addGap(5, 6, 7).addComponent(tUsername, 30, 30, 35)
				.addComponent(separateur1, 1, 1, 1).addGap(20, 25, 30).addComponent(lPassword).addGap(5, 6, 7)
				.addGroup(layout.createParallelGroup().addComponent(tPassword, 30, 30, 35).addComponent(lDisable))

				.addComponent(separateur2, 1, 1, 1)

				.addGap(10, 20, 30).addGroup(layout.createParallelGroup()
						.addComponent(CbShowPassword)
						.addComponent(lForgotPass)

				).addGap(30, 40, 45).addComponent(bValider, 30, 30, 30).addGap(20, 20, Short.MAX_VALUE))

		);

		pLeft.removeAll();
		layout = new GroupLayout(pLeft);
		pLeft.setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(50,100,Short.MAX_VALUE)
				.addComponent(iconLogin)
				.addGap(50,100,Short.MAX_VALUE)
				);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(50,100,Short.MAX_VALUE)
				.addComponent(iconLogin)
				.addGap(50,100,Short.MAX_VALUE)
				);

		this.getContentPane().removeAll();
		layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);

		layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(pLeft, 750, 750, 750)
				.addComponent(pIntern, 300, 300, Short.MAX_VALUE)

		);

		layout.setVerticalGroup(layout.createParallelGroup()

				.addComponent(pLeft, 250, 300, Short.MAX_VALUE)
				.addComponent(pIntern, 300, 300, Short.MAX_VALUE)

		);

	}

//	Traitement des évènements : ajouter des listeners ... 

	private void handleEvents() {
		

		bValider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				bValiderClicked();

			}

		});

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

			}
		});

	}

//	Les méthodes assiciées aux listeners ...

	private void bValiderClicked() {

		String username = tUsername.getText().trim();
		
		@SuppressWarnings("deprecation")
		String password = tPassword.getText().trim();
		
		@SuppressWarnings("deprecation")
		User user = userUTILS.checkuserpassword1(tUsername.getText(), tPassword.getText());
		
		if (username.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'username' ...", "Erreur : Champs vide ...",
					JOptionPane.ERROR_MESSAGE);
			tUsername.requestFocus();// activer le champs code ...
			return;

		}
		if (password.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir le champs 'password' ...", "Erreur : Champs vide ...",
					JOptionPane.ERROR_MESSAGE);
			tPassword.requestFocus();// activer le champs code ...
			return;

		}

		if (user == null) {
			JOptionPane.showMessageDialog(this, "Erreur de connection", "Erreur", JOptionPane.ERROR_MESSAGE);

		} else {
			
			
			UIHome.getSinglton().setVisible(true);
			
			
			// tester is l'utilisateur est une infermiere ou un medecin
			if(user.getType().equals("infermiere")) {
				
				UIHome.lDossier.setVisible(false);
				
			}else if(user.getType().equals("medecin")) {
				
				UIHome.lDossier.setVisible(true);
			}
						
			

		}
		
		

	}

	private void disableMouseClicked(MouseEvent evt) {
		if (lDisable.getIcon() == IconConstants.DISABLE_25_25) {
			lDisable.setIcon(IconConstants.SHOW_25_25);
			tPassword.setEchoChar((char) 0);

		} else if (lDisable.getIcon() == IconConstants.SHOW_25_25) {
			lDisable.setIcon(IconConstants.DISABLE_25_25);
			tPassword.setEchoChar((char) 8226);
		}
	}

	private void lExitMouseClicked(MouseEvent evt) {

	}

	
}
