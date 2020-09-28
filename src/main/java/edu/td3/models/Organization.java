package edu.td3.models;
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


 
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Organization {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
     
    private String name;
    private String domain;
    private String aliases;
    private String settings;


	@OneToMany(mappedBy ="orga",cascade = CascadeType.REMOVE,targetEntity=User.class)
    private List<User> users;
	
    @OneToMany(mappedBy ="orga",cascade = CascadeType.REMOVE,targetEntity=Groupe.class)
    private List<Groupe> groupes;
	
	

	
    
    
    public List<Groupe> getGroupes() {
		return groupes;
	}
	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}
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