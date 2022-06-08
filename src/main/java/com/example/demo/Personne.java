package com.example.demo;

import java.util.ArrayList;

public class Personne {

	public String adresse;
	public String nom;
	public String prenom;
	public String appelsReçus;
	public String appelsEmis;
	public String etatCil;
	public String CV;
	public ArrayList<String> isMinor;

	public ArrayList<String> getIsMinor() {
		return isMinor;
	}
	public void setIsMinor(ArrayList<String> isMinor) {
		this.isMinor = isMinor;
	}
	public String getNom() {
		return nom;
	}

	
	public Personne(String adresse, String nom, String prenom, String appelsReçus, String appelsEmis, String etatCil,
			String cV) {
		super();
		this.adresse = adresse;
		this.nom = nom;
		this.prenom = prenom;
		this.appelsReçus = appelsReçus;
		this.appelsEmis = appelsEmis;
		this.etatCil = etatCil;
		CV = cV;
	}
	
	public Personne(String nom, String prenom, ArrayList<String> isMinor) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.isMinor = isMinor;
	}

	public Personne(String adresse, String nom, String prenom, String appelsReçus, String appelsEmis, String etatCil,
			String cV, ArrayList<String> isMinor) {
		super();
		this.adresse = adresse;
		this.nom = nom;
		this.prenom = prenom;
		this.appelsReçus = appelsReçus;
		this.appelsEmis = appelsEmis;
		this.etatCil = etatCil;
		CV = cV;
		this.isMinor = isMinor;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Personne() {
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getAppelsReçus() {
		return appelsReçus;
	}
	public void setAppelsReçus(String appelsReçus) {
		this.appelsReçus = appelsReçus;
	}
	public String getAppelsEmis() {
		return appelsEmis;
	}
	public void setAppelsEmis(String appelsEmis) {
		this.appelsEmis = appelsEmis;
	}
	public String getEtatCil() {
		return etatCil;
	}
	public void setEtatCil(String etatCil) {
		this.etatCil = etatCil;
	}
	public String getCV() {
		return CV;
	}
	public void setCV(String cV) {
		CV = cV;
	}
	
	
	
	
}
