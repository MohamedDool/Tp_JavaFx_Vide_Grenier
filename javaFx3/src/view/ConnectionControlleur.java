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

public class ConnectionControlleur {

	@FXML
	private Label IdLabel,PassLbl;
	@FXML
	private TextField IdText,PassText;
	@FXML
	private CheckBox loginPersi;
	@FXML
	private Button btnConn, btnConfig;

	private ActionEvent actionEvent;
	private static Config cf= Main.getConf();

	@FXML
	private void editConfig(ActionEvent event) throws IOException{
		cf = Main.viewEditConfig(event, cf);

	}

	@FXML
    private void conBDD(ActionEvent actionEvent) throws IOException   {

		this.actionEvent = actionEvent;
		cf= Main.getConf();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			if(cf == null){
				cf = new Config();
			}
	        String url = "jdbc:mysql://"+cf.getUrl()+":"+cf.getPort()+"/"+cf.getBdd();
			java.sql.Connection cnx=DriverManager.getConnection(url,"root","");
			System.out.println("Connexion à la BDD bien établie ! ");

			Boolean conOk = connexion(cnx);
			if (conOk) {
				Main.viewVideGrenier(this.actionEvent, cf);
			}else{
				Main.viewErreur(this.actionEvent);
				}


		} catch (Exception e) {
			Main.viewErreur(this.actionEvent,"Connection à la Base de donnée échoué");
		}

	}

	private Boolean connexion(java.sql.Connection cnx) throws IOException  {
		// TODO Auto-generated method stub
		Connection cx = null;
		Statement st = null;
		ResultSet rs = null;
		String userId = IdText.getText();
        String password = PassText.getText();
        Boolean conOk = false;

		try {
			st = (Statement) cnx.createStatement();


    	String sql = "select Nom_util,Mdp_util from utilisateur";

    	rs = st.executeQuery(sql);

    	while (rs.next()) {
    		if (userId.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
    			conOk = true;
    		}
    	}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
    		Main.viewErreur(this.actionEvent);
		}
    return conOk;
	}
}
