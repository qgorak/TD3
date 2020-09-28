package edu.td3.controllers;
import java.util.ArrayList;
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
import io.github.jeemv.springboot.vuejs.AbstractVueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;




@Controller



 
public class OrgaController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AbstractVueJS vue;
    
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
		vue.addData("editedIndex",-1);
		vue.addData("dialog",false);
		vue.addMethod("editItem" , "this.editedIndex = this.organizations.indexOf(item)\r\n" + 
				"      this.editedItem = Object.assign({}, item)\r\n" + 
				"      this.dialog = true","item");

		vue.addMethod("save" , "if (this.editedIndex > -1) {\r\n"
				+ "        Object.assign(this.organizations[this.editedIndex], this.editedItem)\r\n"
				+ "        this.$http['post']('http://localhost:8080/rest/orgas/update/'+ this.organizations[this.editedIndex].id,this.editedItem)"
				+ "      } else {\r\n"	
				+ "        this.organizations.push(this.editedItem)\r\n"
				+ "        this.$http['post']('http://localhost:8080/rest/orgas/create', this.editedItem)"
				+ "      }\r\n"
				+ "      this.close()");
		vue.addMethod("close" , " this.dialog = false\r\n"
				+ "      this.$nextTick(() => {\r\n"
				+ "        this.editedItem = Object.assign({}, this.defaultItem)\r\n"
				+ "        this.editedIndex = -1\r\n"
				+ "      })");
		vue.addMethod("deleteItem" , " const index = this.organizations.indexOf(item)\r\n"
				+ "      confirm('Are you sure you want to delete this item?') && this.organizations.splice(index, 1) && this.$http['delete']('http://localhost:8080/rest/orgas/delete/'+ item.id)","item");
		vue.addDataRaw("editedItem", "{ name: '',aliases: '', domain: '',settings: '',users: []}");
		vue.addDataRaw("defaultItem", "{ name: '',aliases: '', domain: '',settings: '',users: []}");
		vue.addComputed("formTitle", "return this.editedIndex === -1 ? 'Nouvelle Organisation' : 'Editer Organisation'");

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
		ModelMap col4 = new ModelMap();
		col4.addAttribute("text", "Actions");
		col4.addAttribute("value", "actions");
		headers.add(col4);
		return headers;
		
	}
	
}