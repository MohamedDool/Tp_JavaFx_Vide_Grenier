package view;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Config;

public class ReservationController {

	@FXML
	private TextField txtNom;

	@FXML
	private TextField txtPrenom;

	@FXML
	private TextField txtAdrs;

	@FXML
	private TextField txtMail;

	@FXML
	private TextField txtPortable;

	@FXML
	private TextField txtDateNaiss;

	@FXML
	private TextField txtDepartement;

	@FXML
	private TextField txtVille;

	@FXML
	private TextField txtIdNum;

	@FXML
	private TextField txtDateLivraison;

	@FXML
	private TextField txtDelivreur;

	@FXML
	private TextField txtImatVehicule;

	@FXML
	private TextField txtNbrEmplacement;

	@FXML
	private CheckBox checkBox;

	@FXML
	private Label lblEditErr;

	@FXML
	private Button btnValider;
	@FXML
	private Button btnRetour;

	String nom,prenom,adresse,mail,portable,dateNaissance,ville,departement,identiteNumero,dateLivraison,immatriculationVehicule,nbrEmplacement;
	private static Config cf = Main.getConf();
	Boolean chkBox, chkBox1, chkBox2, chkBox3, chkBox4;


	@FXML
	public void retour(ActionEvent event) throws Exception{
		Main.viewVideGrenier(event, cf);
	}

	@FXML
	public void insererDonnees(ActionEvent event) throws IOException  {

		 nom = txtNom.getText();
         prenom = txtPrenom.getText();
         adresse = txtAdrs.getText();
         mail = txtMail.getText();
         portable = txtPortable.getText();
         dateNaissance = txtDateNaiss.getText();
         departement = txtDepartement.getText();
         ville = txtVille.getText();
         identiteNumero = txtIdNum.getText();
         dateLivraison = txtDateLivraison.getText();
         immatriculationVehicule = txtImatVehicule.getText();
         nbrEmplacement = txtNbrEmplacement.getText();
         chkBox = checkBox.isSelected();

         Boolean chk = checkRequired();

         if (chkBox && chk) {
	         try {
	        	 insertBdd();
	        	 Main.viewVideGrenier(event, cf);

	         }catch (Exception e) {

				Main.viewEditModif(event, "Insertion à la Base de donnée échoué");


			}
         } else {
        	 Main.viewEditModif(event, "Vous devez certifier sur l'honneur les informations remplies");
         }




	}
	private Boolean checkRequired() {

		if(nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || departement.isEmpty() || ville.isEmpty() || portable.isEmpty()
				|| mail.isEmpty() || nbrEmplacement.isEmpty()){
			return false;

		}else{return true;}

	}

	private void insertBdd() throws SQLException {
		Connection cx = null;
		Statement st = null;
		ResultSet rs = null;
		Integer i = 0;
		Integer j = 0;
		Integer k = 0;

		cx = (Connection) DriverManager.getConnection("jdbc:mysql://"+cf.getUrl()+":"+cf.getPort()+"/"+cf.getBdd(),cf.getMdp(), cf.getPswd());
		st = (Statement) cx.createStatement();

		rs = st.executeQuery("SELECT max(id_exp), max(id_res) from exposant");
		while (rs.next()) {
			i = Integer.parseInt(rs.getString(1)) ;
			j = Integer.parseInt(rs.getString(2));
		}
		i++;
		j++;
		rs = st.executeQuery("SELECT max(NumEmplAttribue_Res) from reservation");
		while (rs.next()) {
			k = Integer.parseInt(rs.getString(1)) ;
		}
		k++;

    	String sql = "INSERT INTO exposant (Id_Exp,Nom_Exp, Prenom_Exp, Adr_Exp, CP_Exp, Ville_Exp, Tel_Exp, Email_Exp, Id_Res) "
    			+ "values ("+i+",'"+nom+"','"+prenom+"','"+adresse+"',"+trsf(departement)+",'"+ville+"',"+trsf(portable)+",'"+mail+"',"+j+")";
    	st.executeUpdate(sql);

    	sql = "INSERT INTO reservation (Id_Res, NbreEmplReserve_Res, TypePaiement_Res, StatutReservation_Res, NumEmplAttribue_Res) "
    			+ "values ("+j+","+nbrEmplacement+",'En ligne','En cours',"+k+")";
    	st.executeUpdate(sql);

	}
	private Integer trsf(String trf) {

		return Integer.parseInt(trf);
	}
}
