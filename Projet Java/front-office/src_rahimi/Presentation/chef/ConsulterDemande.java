package Presentation.chef;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.sun.prism.Image;

public class ConsulterDemande {
	private String jetonString;
	private String procedureString;
	private Boolean etatBoolean;
	private JPanel buttonPanel;
	private JButton traiterButton;
	private Boolean traiterButtonClicked;
	
	private JPanel button2Panel;
	private JButton archiverButton;

	public ConsulterDemande(String jetonString, String procedureString, Boolean etatBoolean) throws IOException {
		super();
		this.traiterButtonClicked = false;
		this.jetonString = jetonString;
		this.procedureString = procedureString;
		this.etatBoolean = etatBoolean;
		this.traiterButton = new JButton("Traiter");
		this.traiterButton.setBackground(Color.white);
		this.buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		p1.setSize(10,10);
	    this.buttonPanel.add(p1, BorderLayout.WEST);
	    this.buttonPanel.add(traiterButton, BorderLayout.CENTER);
	   
	  
	    
	    
	//    Icon icon = new ImageIcon("./images/ar.png");
	  //  this.archiverButton = new JButton(icon);
	    this.archiverButton = new JButton("archiver");
	    this.archiverButton.setBackground(Color.white);
		this.button2Panel = new JPanel();
		button2Panel.setLayout(new BorderLayout());
		JPanel p2 = new JPanel();
		p2.setSize(5,10);
	    this.button2Panel.add(p2, BorderLayout.WEST);
	    this.button2Panel.add(archiverButton, BorderLayout.CENTER);
	}


	public Boolean getEtatBoolean() {
		return etatBoolean;
	}


	public void setEtatBoolean(Boolean etatBoolean) {
		this.etatBoolean = etatBoolean;
	}


	public String getJetonString() {
		return jetonString;
	}
	public void setJetonString(String jetonString) {
		this.jetonString = jetonString;
	}
	public String getProcedureString() {
		return procedureString;
	}
	public JPanel getButtonPanel() {
		return buttonPanel;
	}
	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
	public void setProcedureString(String procedureString) {
		this.procedureString = procedureString;
	}


	public JPanel getButton2Panel() {
		return button2Panel;
	}


	public void setButton2Panel(JPanel button2Panel) {
		this.button2Panel = button2Panel;
	}


	public Boolean getTraiterButtonClicked() {
		return traiterButtonClicked;
	}


	public void setTraiterButtonClicked(Boolean traiterButtonClicked) {
		this.traiterButtonClicked = traiterButtonClicked;
	}

	public void showPopup() {
		showMessageDialog(null, " cette demande est archivé avec succès");
	}
}
