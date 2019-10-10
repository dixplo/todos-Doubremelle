package s4.spring.ds1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.ds1.models.Todos;
import s4.spring.ds1.repositories.TodosRepository;

@Controller
public class TestController {

	@Autowired
	private VueJS vue;
	
	@Autowired
	private TodosRepository repo;
	
	@RequestMapping("/todos")
	public String indexSpa(ModelMap model) {
		List<Todos> todos=repo.findAll();
		vue.addData("items",todos);
		vue.addData("dialog",false);
		vue.addData("message");
		vue.addDataRaw("todo","{label:'',description:''}");
		
		vue.addMethod("addTodos","let self=this;"+Http.post("/todos/add/post", "this.todos","self.dialog=false;"
				+ "self.message='Todos ajoutée';"
				+ "self.items.push(response.data);self.todos={};"));
		
		vue.addMethod("deleteTodos",
				"let self=this;let $='';"+Http.delete("'/todos/delete/'+item.id+$","self.message=response.data;"
						+ "self.items.splice(index,1);",""),"item","index");
		model.put("vue", vue);
		return "todos";
	}
}
