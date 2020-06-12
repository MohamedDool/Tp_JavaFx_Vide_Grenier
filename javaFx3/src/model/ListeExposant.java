package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ListeExposant {

	private SimpleStringProperty Nom;
	private SimpleStringProperty Ville;
	private SimpleStringProperty Emplacement;
	private SimpleStringProperty Email;

	public ListeExposant(String name, String city, String emplacement, String email) {
		this.Nom = new SimpleStringProperty(name);
		this.Ville = new SimpleStringProperty(city);
		this.Emplacement = new SimpleStringProperty(emplacement);
		this.Email = new SimpleStringProperty(email);

	}


	public String getNom() {
		return Nom.get();
	}
	public void setNom(String nom) {
		this.Nom.set(nom);
	}
	public StringProperty nomProperty(){
		return Nom;
	}
	public String getVille() {
		return Ville.get();
	}
	public void setVille(String ville) {
		this.Ville.set(ville);
	}
	public StringProperty villeProperty(){
		return Ville;
	}
	public String getEmplacement() {
		return Emplacement.get();
	}
	public void setEmplacement(String emplacement) {
		this.Emplacement.set(emplacement);
	}
	public StringProperty emplacementProperty(){
		return Emplacement;
	}
	public String getEmail() {
		return Email.get();
	}
	public void setEmail(String email) {
		this.Email.set(email);
	}
	public StringProperty eMailProperty(){
		return Email;
	}
}
