package view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConnectionErreurControlleur {

	@FXML
	private Label lblErreur;
	@FXML
	private Button btnFermer;
	@FXML
	private Button btnRecommencer;

	private void setErreurLbl(String str){
		lblErreur.setText(str);
	}

	public void close(){
		Stage stage = (Stage) btnFermer.getScene().getWindow();

	    stage.close();
	}

	public void recommencer() {
		Main re = new Main();

		try {

			re.start(new Stage());
			// get a handle to the stage

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    Stage stage = (Stage) btnRecommencer.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
}
