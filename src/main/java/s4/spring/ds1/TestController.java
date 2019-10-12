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
	public String indexTodos(ModelMap model) {
		List<Todos> todos=repo.findAll();
		vue.addData("items",todos);
		vue.addData("dialog",false);
		vue.addDataRaw("todos","{todo:'label',description:'',avancement:'',poids:'',}");
		
		vue.addMethod("addTodo", "let self=this;"+Http.post("/rest/todos/create", "this.todos", "self.dialog=false;"
				+ "self.items.push(response.data);self.todo={};"));
		
		vue.addMethod("updateTodo", "let self=this;self.dialog=true;self.todo=todo","todo");
		
		vue.addMethod("deleteTodo", 
					"let self=this;let $='';"+Http.delete("'/rest/todos/'+item.id+$","self.message=response.data;"
					+ "self.items.splice(index,1);"),"item","index");
		model.put("vue", vue);
	return "todos";
	}
}
