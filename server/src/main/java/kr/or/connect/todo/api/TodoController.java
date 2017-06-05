package kr.or.connect.todo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api/todos") 
public class TodoController {	
	 TodoService service; 
	 private final Logger log = LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	public TodoController(TodoService service){
		this.service = service;
	}
	
	//select All query
	@GetMapping
	List<Todo> readList(){
		return service.findAll();
	}
	
	//select By Get Method
	@GetMapping("/{id}")
	Todo read(@PathVariable Integer id){
		return service.findById(id);
	}

	//insert
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Todo create(@RequestBody Todo todo){
		Todo nTodo = service.create(todo);
		log.info("todo created: {}", nTodo);
		return nTodo; //new Todo
	}
	
	//update
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void update(@PathVariable Integer id, @RequestBody Todo todo){
	
		service.update(todo);
		//log.info("todo updated");
	}
	
	//delete
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id){
		service.delete(id);
		log.info("todo deleted");
	}
	
	//delete by completed
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCompleted(){
	//	service.delete();
		service.deleteCompleted();
		log.info("delete completed");
	}
	
	
	
}
