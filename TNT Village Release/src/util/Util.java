package util;

import application.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Util {
	
	public static ObservableList<Film> effettuaRicerca(String parolaChiave, ObservableList<Film> tableView) {

		ObservableList<Film> listaModificata = FXCollections.observableArrayList();
		
		for (int i = 0; i < tableView.size(); i++) {
			if(tableView.get(i).getTitolo().contains(parolaChiave)) {
				listaModificata.add(tableView.get(i));
			}
		}
		
		listaModificata.toString();
		return listaModificata;
	}
}
