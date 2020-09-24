package edu.td3.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import edu.td3.models.Organization;
import edu.td3.repositories.GroupRepository;
import edu.td3.repositories.OrgaRepository;
import edu.td3.repositories.UserRepository;
import io.github.jeemv.springboot.vuejs.VueJS;








 
@Controller
@RequestMapping("/rest/")
public class OrgaController {
	@Autowired
    private OrgaRepository repo;
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private GroupRepository gRepo;
	
	@Autowired
	private VueJS vue;
	
	@GetMapping("/orgas/")
	public String read(ModelMap model) {

	    List<Organization> organizations = repo.findAll();
		vue.addData("organizations", organizations);
		model.put("vue", vue);
		return "index";
	}
	
	@GetMapping("/orgas/{id}")
	public String read(@PathVariable int id,ModelMap model) {
		Organization orga = repo.findById(id);
		vue.addData("orga", orga);
		model.put("vue", vue);
		return "index";
	}
	
	@PostMapping("/orgas/create")
    public void create(@ModelAttribute Organization orga) {
		Organization e = orga;
		repo.saveAndFlush(e);
   
    }
	
	@DeleteMapping("/orgas/delete/{id}")
    public RedirectView delete(@PathVariable int id) {
		repo.deleteById(id);
        return new RedirectView("/orgas/");
    }
	
}