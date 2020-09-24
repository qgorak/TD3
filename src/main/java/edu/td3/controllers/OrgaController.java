package edu.td3.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.td3.models.Organization;
import edu.td3.repositories.GroupRepository;
import edu.td3.repositories.OrgaRepository;
import edu.td3.repositories.UserRepository;
import io.github.jeemv.springboot.vuejs.VueJS;








 
@Controller
@RequestMapping("/orgas/")
public class OrgaController {
	@Autowired
    private OrgaRepository repo;
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private GroupRepository gRepo;
	
	@Autowired
	private VueJS vue;
	
	@GetMapping("/")
	public String index(ModelMap model) {
	    List<Organization> organizations = repo.findAll();
		vue.addData("organizations", organizations);
		model.put("vue", vue);
		return "index";
	}
	
}