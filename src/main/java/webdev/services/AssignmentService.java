package webdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Assignment;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {

	@Autowired
	AssignmentRepository repository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments() {
		return (List<Assignment>) repository.findAll();
	}
	
	
	@GetMapping("/api/widget/{assignmentId}")
	public Assignment findWidgetById(@PathVariable("assignmentId") int id) {
		Optional<Assignment> data = repository.findById(id);
		if(data.isPresent()) 
			return data.get();
		Assignment a = new Assignment();
		a.setTitle("None");
		return a;
	}

	@GetMapping("/api/topic/{topicId}/assignment")
	public List<Assignment> findAllAssignmentWidgetsForTopic(@PathVariable("topicId") int id) {
		Optional<Topic> data = topicRepository.findById(id);
		List<Assignment> list = new ArrayList<>();
		if(data.isPresent()) {
			List<Widget> widgets = data.get().getWidgets();
			for(Widget widget : widgets) {	
				if(widget instanceof Assignment) {
					list.add((Assignment)widget);
				}
			}	
		}
		return list;
	}
	
	@PostMapping("/api/topic/{topicId}/assignment")
	public Assignment create(@PathVariable("topicId") int id, @RequestBody Assignment assignment) {
		Optional<Topic> data = topicRepository.findById(id);
		if(data.isPresent()) {
			assignment.setTopic(data.get());
			repository.save(assignment);
		}
		return assignment;
	}
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignmentById(@PathVariable("assignmentId") int id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/api/assignment/{assignmentId}")
	public void updateAssignmentById(@PathVariable("assignmentId") int id, @RequestBody Assignment assignment) {
		Optional<Assignment> data = repository.findById(id);
		if(data.isPresent()) {
			Assignment newAsm = data.get();
			newAsm.setPoints(assignment.getPoints());
			newAsm.setTitle(assignment.getTitle());
			newAsm.setDescription(assignment.getDescription());
			repository.save(newAsm);
		}
	}
}
