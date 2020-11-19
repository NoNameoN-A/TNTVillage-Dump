package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import util.Util;

public class Application implements Initializable{
    @FXML private TextField textField;
    
    @FXML private TableView<Film> tableView;
	    @FXML private TableColumn<Film, String> titolo;
	    @FXML private TableColumn<Film, String> data;
	    @FXML private TableColumn<Film, String> dimensione;
	    @FXML private TableColumn<Film, String> autore;
	    @FXML private TableColumn<Film, String> post;
	    @FXML private TableColumn<Film, String> hash;
	    @FXML private TableColumn<Film, String> descrizione;
	    @FXML private TableColumn<Film, String> topic;
	    
    private static ObservableList<Film> incastratoreDiFilm = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		data.setCellValueFactory(new PropertyValueFactory<Film, String>("data"));
		hash.setCellValueFactory(new PropertyValueFactory<Film, String>("hash"));
		topic.setCellValueFactory(new PropertyValueFactory<Film, String>("topic"));
		post.setCellValueFactory(new PropertyValueFactory<Film, String>("post"));
		autore.setCellValueFactory(new PropertyValueFactory<Film, String>("autore"));
		titolo.setCellValueFactory(new PropertyValueFactory<Film, String>("titolo"));
		descrizione.setCellValueFactory(new PropertyValueFactory<Film, String>("descrizione"));
		dimensione.setCellValueFactory(new PropertyValueFactory<Film, String>("dimensione"));
	
	}

    private ObservableList<Film> creaTableViewFilm(String csvFilePath) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFilePath));
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                String data = country[0];
                String hash = country[1];
                String topic = country[2];
                String post = country[3];
                String autore = country[4];
                String titolo = country[5];
                String descrizione = country[6];
                String dimensione = country[7];
                incastratoreDiFilm.add(new Film(data, hash, topic, post, autore, titolo, descrizione, dimensione));
            }
        } catch (Exception e) {/**/} 
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return incastratoreDiFilm;
	}

	@FXML
    void carica(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(null);
		String path = null;
        if (file != null) {
        	path = file.getPath();
        }
       
        incastratoreDiFilm = this.creaTableViewFilm(path);
        tableView.setItems(incastratoreDiFilm);
    }

    @FXML
    void cerca(ActionEvent event) {
    	tableView.setItems(incastratoreDiFilm);
    	tableView.setItems(Util.effettuaRicerca(textField.getText(), tableView.getItems()));
    }

    @FXML
    void scarica(ActionEvent event) {
    	try {
	    	StringBuilder URL = new StringBuilder();
	    	Film selezionato = tableView.getSelectionModel().getSelectedItem();
	    	URL.append("magnet:?xt=urn:btih:" + selezionato.getHash() + "&dn=" + selezionato.getTitolo());
	    	App.connection(URL.toString());
    	} catch(Exception e) {/**/}
    }
	
	@FXML
    void about(ActionEvent event) {
		try {
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Sviluppatore");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Ciao! Sono un ragazzo iscritto al corso d'Informatica all'università della Calabria.");
	    	ButtonType inviaUnaEmail = new ButtonType ("Invia una email");
	    	alert.getButtonTypes().setAll(inviaUnaEmail);
	    	
	    	Optional<ButtonType> opzioneScelta = alert.showAndWait();
	    	if(opzioneScelta.get() == inviaUnaEmail) {
	    		App.inviaEmail();
	    	}
		} catch(Exception e) {/**/}
	}
	
	@FXML
    void ripristina(ActionEvent event) {
    	tableView.setItems(incastratoreDiFilm);
	}

}
