package s4.spring.ds1.rest;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.ds1.models.Todos;
import s4.spring.ds1.repositories.TodosRepository;

@RestController
@RequestMapping("/rest/todos")
public class RestTodosController {
	
	@Autowired
	private TodosRepository todosRepository;
	
	@GetMapping("")
	public @ResponseBody List<Todos> read(){
		return todosRepository.findAll();
	}
	
	@GetMapping("{id}")
	public @ResponseBody Todos read(@PathVariable int id){
		Optional<Todos> opt=todosRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	@PostMapping("create")
	public @ResponseBody Todos create(@RequestBody Todos orga) {
		todosRepository.save(orga);
		return orga;
	}
	
	@PutMapping("update")
	public @ResponseBody Todos update(@RequestBody Todos orga) {
		todosRepository.save(orga);
		return orga;
	}
	
	@DeleteMapping("{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable int id) {
		todosRepository.deleteById(id);
		return new ResponseEntity<String>("Suppression réussie !", HttpStatus.OK);
	}

}
