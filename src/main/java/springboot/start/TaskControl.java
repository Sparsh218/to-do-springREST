package springboot.start;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class TaskControl {

	@Autowired
	TasksDao taskDao;
	
	@GetMapping("tasks")
	public List<Task> getTasksList() {
		List<Task> tasks = taskDao.findAll();
		return tasks;
	}
	
	@PostMapping(path="addTask", consumes= {"application/json"})
	public void addTask(@RequestBody Task task) {
		System.out.println("title of task is : "+task.isChecked());
		String date = task.getDate();
		task.setDate(date.substring(0, date.indexOf("T")));
		taskDao.save(task);
	}
	
	@PutMapping(path="updateStatus/{id}", consumes= {"application/json"})
	public void updateStatus(@PathVariable("id") int taskId, @RequestBody Task task) {
		System.out.println("update task");
		taskDao.delete(taskDao.getOne(taskId));
		taskDao.save(task);
	}
	
	@DeleteMapping(path="deleteTask/{id}", consumes= {"application/json"})
	public int deleteTask(@PathVariable("id") int taskId) {
		System.out.println("remove task");
		taskDao.delete(taskDao.getOne(taskId));
		return 1;	
	}
	
}
