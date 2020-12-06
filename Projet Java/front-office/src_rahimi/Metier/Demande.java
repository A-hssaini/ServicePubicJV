package Metier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mongodb.util.JSON;

public class Demande {
	String jeton;//////id
	String procedure;
	ArrayList<Etape> etapes;
	private JPanel buttonPanel;
	private JButton traiterButton;
	private Boolean traiterButtonClicked;

	public Demande() {
		super();
		
		this.traiterButton = new JButton("Traiter");
		this.traiterButton.setBackground(Color.white);
		this.buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		p1.setSize(10, 10);
		this.buttonPanel.add(p1, BorderLayout.WEST);
		this.buttonPanel.add(traiterButton, BorderLayout.CENTER);
	}

	@Override
	public String toString() {
		return "Demande [jeton=" + jeton +", procedureString=" + procedure + ", document="
				+ etapes + "]";
	}

	public String getJeton() {
		return jeton;
	}

	public void setJeton(String jeton) {
		this.jeton = jeton;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public ArrayList<Etape> getEtapes() {
		return etapes;
	}

	public void setEtapes(ArrayList<Etape> etapes) {
		this.etapes = etapes;
	}

	public JButton getTraiterButton() {
		return traiterButton;
	}

	public void setTraiterButton(JButton traiterButton) {
		this.traiterButton = traiterButton;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
	
}
