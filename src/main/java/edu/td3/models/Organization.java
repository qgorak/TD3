package edu.td3.models;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


 
@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
     
    private String name;
    private String domain;
    private String aliases;
    private String settings;

	@OneToMany(mappedBy ="orga",cascade = CascadeType.REMOVE)
    private List<User> users;
    @OneToMany(cascade=CascadeType.ALL,mappedBy="orga")
    private List<Groupe> groupes;
	
    
    
    public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void addUser(User user) {
		this.users.add(user);
	}


    




    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getAliases() {
		return aliases;
	}
	public void setAliases(String aliases) {
		this.aliases = aliases;
	}
	public String getSettings() {
		return settings;
	}
	public void setSettings(String settings) {
		this.settings = settings;
	}
	@Override

	public String toString() {
		return this.name;
	}


}