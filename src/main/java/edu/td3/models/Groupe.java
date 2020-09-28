package edu.td3.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

	private String name;
	
    @ManyToOne
    private Organization orga;
    
    @ManyToMany
    @JoinTable(name = "user_group")
    private List<User> users;
    


	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setOrga(Organization orga) {
		this.orga = orga;
	}
	
	@Override

	public String toString() {
		return this.name;
	}

}