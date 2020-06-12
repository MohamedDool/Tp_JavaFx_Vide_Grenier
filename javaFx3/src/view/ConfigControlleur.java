package view;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Config;

public class ConfigControlleur {

	private Config cf = new Config();

	@FXML
	private TextField txtLogin ;

	@FXML
	private TextField txtPasswd;
	@FXML
	private TextField txtUrl;
	@FXML
	private TextField txtPort;
	@FXML
	private TextField txtBdd;
	@FXML
	private Button btnValiderConf;
	@FXML
	private Button btnRetour;



	@FXML
	private void validerConf(ActionEvent event) throws IOException{
		cf.setMdp(txtLogin.getText());
		cf.setPswd(txtPasswd.getText());
		cf.setUrl(txtUrl.getText());
		cf.setPort(txtPort.getText());
		cf.setBdd(txtBdd.getText());
		Main.viewConnection(event, cf);
	}

	@FXML
	private void retourLogin(ActionEvent event){

	}

	public TextField getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(TextField txtLogin) {
		this.txtLogin = txtLogin;
	}

	public TextField getTxtPasswd() {
		return txtPasswd;
	}

	public void setTxtPasswd(TextField txtPasswd) {
		this.txtPasswd = txtPasswd;
	}

	public TextField getTxtUrl() {
		return txtUrl;
	}

	public void setTxtUrl(TextField txtUrl) {
		this.txtUrl = txtUrl;
	}

	public TextField getTxtPort() {
		return txtPort;
	}

	public void setTxtPort(TextField txtPort) {
		this.txtPort = txtPort;
	}

	public TextField getTxtBdd() {
		return txtBdd;
	}

	public void setTxtBdd(TextField txtBdd) {
		this.txtBdd = txtBdd;
	}

}
