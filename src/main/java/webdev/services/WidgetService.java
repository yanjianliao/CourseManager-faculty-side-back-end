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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Lesson;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.CourseRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>)widgetRepository.findAll();
	}
	
	
	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findAllWidgetsByTopicId(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			return data.get().getWidgets();
		}
		return new ArrayList<>();
	}
	
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			return data.get();
		}
		return new Widget();
	}
	
	@PostMapping("/api/topic/{topicId}/widget")
	public List<Widget> createWidgets(@PathVariable("topicId") int topicId, @RequestBody List<Widget> widgets) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			for(Widget widget : topic.getWidgets()) {
				widgetRepository.deleteById(widget.getId());
			}
			
			for(Widget widget : widgets) {
				widget.setTopic(topic);
				widgetRepository.save(widget);	
			}
		}
		return widgets;
	}
	
	
	@PostMapping("/api/widget/save")
	public void saveWidgets(@RequestBody List<Widget> widgets) {
		widgetRepository.deleteAll();
		for(Widget w : widgets) {
			widgetRepository.save(w);
		}
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidgetById(@PathVariable("widgetId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}
}
