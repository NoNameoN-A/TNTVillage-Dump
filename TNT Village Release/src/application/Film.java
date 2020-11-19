package application;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javafx.beans.property.SimpleStringProperty;

public class Film {
	
	private SimpleStringProperty data;
	private SimpleStringProperty hash;
	private SimpleStringProperty topic;
	private SimpleStringProperty post;
	private SimpleStringProperty autore;
	private SimpleStringProperty titolo;
	private SimpleStringProperty descrizione;
	private SimpleStringProperty dimensione;
	
	public Film(String data, String hash, String topic, String post, String autore, String titolo, String descrizione, String dimensione) {
		super();
		this.data = new SimpleStringProperty(data);
		this.hash = new SimpleStringProperty(hash);
		this.topic = new SimpleStringProperty(topic);
		this.post = new SimpleStringProperty(post);
		this.autore = new SimpleStringProperty(autore);
		this.titolo = new SimpleStringProperty(titolo);
		this.descrizione = new SimpleStringProperty(descrizione);
		NumberFormat nf = new DecimalFormat("###,###.##");
		try {
			Double temp = Double.parseDouble(dimensione)/1000000.0;
			this.dimensione = new SimpleStringProperty(nf.format(temp) + "MB");
		}catch(Exception e) {
			this.dimensione = new SimpleStringProperty("Indefinita");
		}
	}

	public String getData() { return data.get(); }
	public void setData(SimpleStringProperty data) { this.data = data; }
	public String getHash() { return hash.get(); }
	public void setHash(SimpleStringProperty hash) { this.hash = hash; }
	public String getTopic() { return topic.get(); }
	public void setTopic(SimpleStringProperty topic) { this.topic = topic; }
	public String getPost() { return post.get(); }
	public void setPost(SimpleStringProperty post) { this.post = post; }
	public String getAutore() { return autore.get(); }
	public void setAutore(SimpleStringProperty autore) { this.autore = autore; }
	public String getTitolo() { return titolo.get(); }
	public void setTitolo(SimpleStringProperty titolo) { this.titolo = titolo; }
	public String getDescrizione() { return descrizione.get(); }
	public void setDescrizione(SimpleStringProperty descrizione) { this.descrizione = descrizione; }
	public String getDimensione() { return dimensione.get(); }
	public void setDimensione(SimpleStringProperty dimensione) { this.dimensione = dimensione; }

	public Film clona() {
		try {
			return (Film) this.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
