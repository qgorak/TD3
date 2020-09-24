package edu.td3.controllers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.td3.models.Organization;
import io.github.jeemv.springboot.vuejs.VueJS;




@Controller



 
public class OrgaController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private VueJS vue;
    
	@RequestMapping("/orgas/")
    public String index(ModelMap model) {
		
	    final String URL = "http://localhost:8080/rest/orgas/";
		ResponseEntity<List<Organization>> responseEntity = restTemplate.exchange(
			    URL, 
			    HttpMethod.GET, 
			    null, 
			    new ParameterizedTypeReference<List<Organization>>() {
			    });
		List<Organization> Organizations = responseEntity.getBody();
           
	    vue.addData("organizations", Organizations);
	
		vue.addData("headers", GenHeaders());

		vue.addData("organizations",Collections.emptyList());
		vue.addData("editedIndex",-1);
		vue.addMethod("editItem(item)" , "this.editedIndex = this.desserts.indexOf(item)\r\n" + 
				"      this.editedItem = Object.assign({}, item)\r\n" + 
				"      this.dialog = true");
		vue.addDataRaw("editedItem", "{name: '',calories: 0,fat: 0,carbs: 0,protein: 0}");
		vue.addDataRaw("defaultItem", "{name: '',calories: 0,fat: 0,carbs: 0,protein: 0}");
		vue.addComputed("formTitle", "return this.editedIndex === -1 ? 'New Item' : 'Edit Item'");

	    model.put("vue", vue);
        return "index";
       }
	
	public List<ModelMap> GenHeaders() {
		List<ModelMap> headers = new ArrayList<ModelMap>();
		ModelMap col = new ModelMap();
		col.addAttribute("text", "Organizations");
		col.addAttribute("align", "start");
		col.addAttribute("sortable", false);
		col.addAttribute("value", "name");
		headers.add(col);
		ModelMap col2 = new ModelMap();
		col2.addAttribute("text", "Aliases");
		col2.addAttribute("value", "aliases");
		headers.add(col2);
		ModelMap col3 = new ModelMap();
		col3.addAttribute("text", "Domain");
		col3.addAttribute("value", "domain");
		headers.add(col3);
		return headers;
		
	}
	
}