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

import webdev.models.Exam;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.ExamRepository;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository repository;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>)repository.findAll();
	}
	
	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int id) {
		Optional<Exam> data = repository.findById(id);
		if(data.isPresent())
			return data.get();
		
		return new Exam();
	}
	
	@GetMapping("/api/topic/{topicId}/exam")
	public List<Exam> findAllExamsForTopic(@PathVariable("topicId") int id) {
		Optional<Topic> data = topicRepository.findById(id);
		List<Exam> list = new ArrayList<>();
		if(data.isPresent()) {
			List<Widget> widgets = data.get().getWidgets();
			for(Widget widget : widgets) {	
				if(widget instanceof Exam) {
					list.add((Exam)widget);
				}
			}	
		}
		return list;
	}
	
	@PostMapping("/api/topic/{topicId}/exam")
	public Exam createExamForTopic(@PathVariable("topicId") int id, @RequestBody Exam exam) {
		Optional<Topic> data = topicRepository.findById(id);
		if(data.isPresent()) {
			exam.setTopic(data.get());
			repository.save(exam);
		}
		return exam;
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExamById(@PathVariable("examId") int id) {
		repository.deleteById(id);
	}
	
	
}
