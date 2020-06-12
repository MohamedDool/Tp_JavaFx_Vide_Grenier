package application;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Config;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {

	private Stage primaryStage;
    private AnchorPane rootLayout;
    private static Config cf;

	@Override
	public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initLog();

       // showPersonOverview();
	}

	private void initLog() {
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/Connection.fxml"));
            rootLayout =  loader.load();
            cf = getConf();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static String countNbrPlace() throws Exception{
		Connection cx = null;
		Statement st = null;
		ResultSet rs = null;
		String i ="";

		cx = (Connection) DriverManager.getConnection("jdbc:mysql://"+cf.getUrl()+":"+cf.getPort()+"/"+cf.getBdd(),cf.getMdp(), cf.getPswd());
		st = (Statement) cx.createStatement();

		rs = st.executeQuery("SELECT COUNT(id_exp) from exposant");
		while (rs.next()) {
			i = rs.getString(1);
		}
		int cal = Integer.parseInt(i);
		cal = 150 - cal;
		i = Integer.toString(cal);
		return i;
	}

	public static void viewVideGrenier(ActionEvent actionEvent, Config cf2) throws IOException  {

		cf = cf2;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/VideGrenier.fxml"));
		AnchorPane rootLayout =  loader.load();
		Scene scene = new Scene(rootLayout);
		Node  source = (Node)  actionEvent.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();

		try {
			Label lblData = (Label) rootLayout.lookup("#lblPlaceRestante");
			lblData.setText(Main.countNbrPlace()+" /150");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        stage.setScene(scene);
        stage.show();
	}

	public static void viewErreur(ActionEvent actionEvent, String string) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/ConnectionErreur.fxml"));
		AnchorPane rootLayout = loader.load();
		Scene scene = new Scene(rootLayout);
		Node  source = (Node)  actionEvent.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		Label lblData = (Label) rootLayout.lookup("#lblErreur");
		lblData.setText(string);
        stage.setScene(scene);
        stage.show();

	}

	public static void viewErreur(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/ConnectionErreur.fxml"));
		AnchorPane rootLayout = loader.load();
		Scene scene = new Scene(rootLayout);
		Node  source = (Node)  actionEvent.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		Label lblData = (Label) rootLayout.lookup("#lblErreur");
		lblData.setText("Login ou Mot de passe incorrect");
        stage.setScene(scene);
        stage.show();


	}

	public static void viewEditModif(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/EditModif.fxml"));
		AnchorPane rootLayout = loader.load();
		Scene scene = new Scene(rootLayout);
		Node  source = (Node)  event.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
	}

	public static void viewEditModif(ActionEvent actionEvent, String string) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/EditModif.fxml"));
		AnchorPane rootLayout = loader.load();
		Scene scene = new Scene(rootLayout);
		Node  source = (Node)  actionEvent.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		Label lblData = (Label) rootLayout.lookup("#lblEditErr");
		lblData.setText("Toutes les Données Obligatoires ne sont pas renseignées");
        stage.setScene(scene);
        stage.show();
	}

	public static Config  viewEditConfig(ActionEvent event, Config cf2) throws IOException {
		cf = cf2;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/ConfigBdd.fxml"));
		AnchorPane rootLayout = loader.load();

		Scene scene = new Scene(rootLayout);
		Node  source = (Node)  event.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		if(cf == null){
			chargeConfig(rootLayout, new Config());
		}else{chargeConfig(rootLayout, cf);}

        stage.setScene(scene);
        stage.show();
        return cf;


	}

	private static void chargeConfig(AnchorPane rootLayout2, Config cf2) {
		cf = cf2;
		TextField login = (TextField) rootLayout2.lookup("#txtLogin");
		login.setText(cf.getMdp());

		TextField pswd = (TextField) rootLayout2.lookup("#txtPasswd");
		pswd.setText(cf.getPswd());

		TextField url = (TextField) rootLayout2.lookup("#txtUrl");
		url.setText(cf.getUrl());

		TextField port = (TextField) rootLayout2.lookup("#txtPort");
		port.setText(cf.getPort());

		TextField bdd = (TextField) rootLayout2.lookup("#txtBdd");
		bdd.setText(cf.getBdd());

	}

	public static void viewConnection(ActionEvent event, Config cf2) throws IOException {
		cf = cf2;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/Connection.fxml"));
		AnchorPane rootLayout = loader.load();
		Scene scene = new Scene(rootLayout);
		Node  source = (Node)  event.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
	}

	public static Config getConf() {
		return cf;
	}


}
