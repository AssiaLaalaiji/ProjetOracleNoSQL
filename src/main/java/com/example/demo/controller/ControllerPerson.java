package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Personne;

import oracle.kv.Direction;
import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;
import oracle.kv.Key;
import oracle.kv.KeyValueVersion;
import oracle.kv.Value;
import oracle.kv.ValueVersion;

@Controller
public class ControllerPerson {	


		private final KVStore store=KVStoreFactory.getStore(new KVStoreConfig("kvstore", "localhost" + ":" + "5000")); 

		@PostMapping("/savePersonne")
		public String ajoutEmployee(Personne personne) {
			
	    List<String> majorPath = new ArrayList<String>();
		majorPath.add(personne.getNom()); 
		majorPath.add(personne.getPrenom());
		ArrayList<String> list=new ArrayList<>();
				list.add(personne.getAdresse());
				list.add(personne.getAppelsEmis());
				list.add(personne.getAppelsReçus());
				list.add(personne.getCV());
				list.add(personne.getEtatCil());
				System.out.println(list);
		ArrayList<String> list2=new ArrayList<>();
				list2.add("Adress");
				list2.add("AppelsEmis");
				list2.add("AppelsReçus");
				list2.add("CV");
				list2.add("EtatCil");
		int i=0;		
		for (String p: list) {
			if (p!=null && p!="") {
			Value myValue = Value.createValue(p.getBytes());
			Key myKey = Key.createKey(majorPath, list2.get(i));
            
			final ValueVersion valueVersion = store.get(myKey);
			System.out.println(myKey+"   ===>   "+p);
		 	store.put(myKey,myValue);}
			i++;
	   }
		return "redirect:/AfficherPersonne" ;
       }
		
		@GetMapping("/AfficherPersonne")
		public String afficherPersonnes(Model m) {
			ArrayList<String> list2=new ArrayList<>();
			list2.add("Adress");
			list2.add("AppelsEmis");
			list2.add("AppelsReçus");
			list2.add("CV");
			list2.add("EtatCil");
			ArrayList<Personne> Personnes = new ArrayList<Personne>();
			Iterator<Key> iterator = store.storeKeysIterator(Direction.UNORDERED, 100);
			System.out.println("nom				|		prenom				|		Column						|		Value				");
			while(iterator.hasNext()) {
				Key iterNext = iterator.next();
				if (!iterNext.toString().startsWith("/r") && !iterNext.toString().startsWith("/s") && iterNext.getFullPath().size()==3){
					//System.out.println(iterNext.getFullPath());
				    List<String> Major=new ArrayList<String>();
					Major.add(iterNext.getFullPath().get(0));
					Major.add(iterNext.getFullPath().get(1));
						Key myKey = Key.createKey(Major, iterNext.getFullPath().get(2));
						ValueVersion vv = store.get(myKey);
						if(vv!=null) {
						Value v = vv.getValue();
				        String data = new String(v.getValue());
				        ArrayList<String> dictKV = new ArrayList<String>();
				        dictKV.add(iterNext.getFullPath().get(2));
				        dictKV.add(data);
				        Personnes.add(new Personne(iterNext.getFullPath().get(0),iterNext.getFullPath().get(1),dictKV));
				        m.addAttribute("personnes", Personnes);
				        
						}
					
					    
			}}
		
		    return "personnes"; 
		                               }
		@GetMapping("/AjoutPersonne")
		public String ajoutEmp(Model m) {
			Personne personne = new Personne();
			m.addAttribute("personne", personne);
		    return "Ajout"; 
		                               }
		
		@GetMapping("/updatePersonne")
		public String updatePersonne(String nom,String prenom,String minor0,String minor1,Model m) {
			
    		Personne presonne = new Personne();
    		presonne.setNom(nom);
    		presonne.setPrenom(prenom);
            ArrayList<String> dict = new ArrayList<>();
            dict.add(minor0);
            dict.add(minor1);
            
    		presonne.setIsMinor(dict);
    		switch (minor0) {
			case "Adress":
				presonne.setAdresse(minor1);
				break;
			case "appelsReçus":
				presonne.setAppelsReçus(minor1);
				break;
			case "appelsEmis":
				presonne.setAppelsEmis(minor1);
				break;
				case "etatCil":
					presonne.setEtatCil(minor1);
					break;
				case "CV":
					presonne.setCV(minor1);
					break;
		
			}
            m.addAttribute("personne", presonne)  ; 
            return "Ajout" ; 
		                               }
		
		
		@GetMapping("/deletePersonne")
		public String deletePersonne(String nom,String prenom,String minor) {
			
            ArrayList<String> MajorPath = new ArrayList<String>();
            MajorPath.add(nom);
            MajorPath.add(prenom);
    		Key myKey = Key.createKey(MajorPath, minor);
            store.delete(myKey);
            System.out.println(nom+"deleted");
            return "redirect:/AfficherPersonne" ; 
		                               }
		
		@GetMapping("/deleteAll")
		public String delete() {
			Iterator<Key> iterator = store.storeKeysIterator(Direction.UNORDERED, 100);
			while(iterator.hasNext()) {
				Key iterNext = iterator.next();
				if (!iterNext.toString().startsWith("/r") && !iterNext.toString().startsWith("/s")  && iterNext.getFullPath().size()==3 ){
					//System.out.println(iterNext.getFullPath());
				    List<String> Major=new ArrayList<String>();
					Major.add(iterNext.getFullPath().get(0));
					Major.add(iterNext.getFullPath().get(1));
						Key myKey = Key.createKey(Major, iterNext.getFullPath().get(2));
						 store.delete(myKey);
			            

						}
					
					    
			}
    		
            return "redirect:/AfficherPersonne";
		}
		
}
