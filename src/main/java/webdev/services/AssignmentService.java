package webdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//@GetMapping()

	@GetMapping("/api/topic/{topicId}/assignment")
	public List<Assignment> findAllAssignmentWidgetsForTopic(@PathVariable("topicId") int id) {
		Optional<Topic> data = topicRepository.findById(id);
		List<Assignment> list = new ArrayList<>();
		if(data.isPresent()) {
			List<Widget> widgets = data.get().getWidgets();
			for(Widget widget : widgets) {
				System.out.println(widget.getName());
				if(widget instanceof Assignment) {
					list.add((Assignment)widget);
				}
			}
			
		}
		return list;
	}
	@GetMapping("/api/topic/{topicId}/assignment/create")
	public List<Assignment> create(@PathVariable("topicId") int id) {
		Optional<Topic> data = topicRepository.findById(id);
		List<Assignment> list = new ArrayList<>();
		if(data.isPresent()) {
			
			Assignment temp = new Assignment();
			temp.setTitle("test");
			temp.setTopic(data.get());
			repository.save(temp);
			
		}
		return list;
	}
	
}
