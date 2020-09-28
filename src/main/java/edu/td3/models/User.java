package edu.td3.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private String name;
    @ManyToOne
    private Organization orga;
    
    @ManyToMany(mappedBy="users")
    private List<Groupe> groupes;

	public Organization getOrga() {
		return orga;
	}
	public void setOrga(Organization orga) {
		this.orga = orga;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

}