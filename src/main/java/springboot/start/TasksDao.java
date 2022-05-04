package springboot.start;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface TasksDao extends JpaRepository<Task, Integer>{

}
