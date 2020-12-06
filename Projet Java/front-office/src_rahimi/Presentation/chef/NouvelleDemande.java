package Presentation.chef;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import static javax.swing.JOptionPane.showMessageDialog;

public class NouvelleDemande {
	private String cinString;
	private String procedureString;
	private JPanel buttonPanel;
	private JButton genererJetonButton;

	public NouvelleDemande(String cinString, String procedureString) {
		super();
		this.cinString = cinString;
		this.procedureString = procedureString;
		this.genererJetonButton = new JButton("genere Jeton");
		this.genererJetonButton.setBackground(Color.white);
		this.buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		p1.setSize(10,10);
	    this.buttonPanel.add(p1, BorderLayout.WEST);
	    this.buttonPanel.add(genererJetonButton, BorderLayout.CENTER);
	}
	public String getCinString() {
		return cinString;
	}
	public void setCinString(String cinString) {
		this.cinString = cinString;
	}
	public String getProcedureString() {
		return procedureString;
	}
	public void setProcedureString(String procedureString) {
		this.procedureString = procedureString;
	}
	public JPanel getButtonPanel() {
		return buttonPanel;
	}
	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
	
	public void showPopup() {
		showMessageDialog(null, "jeton a été généré avec succés");
	}
}
