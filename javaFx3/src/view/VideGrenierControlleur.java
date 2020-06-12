package view;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Config;
import model.ListeExposant;

public class VideGrenierControlleur {

    @FXML
    private TableView<ListeExposant> tablReser;
    @FXML
    private TableColumn<ListeExposant, String> nomCol;
    @FXML
    private TableColumn<ListeExposant, String> emplacementCol;
    @FXML
    private TableColumn<ListeExposant, String> emailCol;
    @FXML
    private TableColumn<ListeExposant, String> villeCol;
    @FXML
    private Label lblPlaceRestante;
    @FXML
    private MenuItem mnEditRes;


	private ObservableList<Object> data;
	private static Config cf = Main.getConf();

	@FXML
	public void edit(ActionEvent actionEvent) throws IOException{


		Main.viewEditModif(actionEvent);

	}

    @FXML
    public void initialize() throws SQLException {
    	Connection cx = null;
		Statement st = null;
		ResultSet rs = null;

		cx = (Connection) DriverManager.getConnection("jdbc:mysql://"+cf.getUrl()+":"+cf.getPort()+"/"+cf.getBdd(),cf.getMdp(), cf.getPswd());
		st = (Statement) cx.createStatement();

    	String sql = "select CONCAT(Nom_Exp , ' ' , Prenom_Exp) as Nom, Ville_Exp, NbreEmplReserve_Res, Email_Exp FROM exposant LEFT JOIN reservation ON exposant.Id_Res=reservation.Id_Res;";

    	rs = st.executeQuery(sql);

    	ObservableList<ListeExposant> row = FXCollections.observableArrayList();
    	nomCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
    	emailCol.setCellValueFactory(cellData -> cellData.getValue().eMailProperty());
    	emplacementCol.setCellValueFactory(cellData -> cellData.getValue().emplacementProperty());
    	villeCol.setCellValueFactory(cellData -> cellData.getValue().villeProperty());

    	while (rs.next()){
    		int i = 0;
    		ListeExposant exposant = new ListeExposant(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4));

    		row.add(exposant);

    	}
    	tablReser.setItems(row);
    	tablReser.refresh();

    }
}
