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

import webdev.models.BaseExamQuestion;
import webdev.models.Choice;
import webdev.models.Exam;
import webdev.models.MultipleChoiceExamQuestion;
import webdev.repositories.BaseExamQuestionRepository;
import webdev.repositories.ChoiceRepository;
import webdev.repositories.ExamRepository;
import webdev.repositories.MultipleChoiceExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceExamQuestionService {

	@Autowired
	ChoiceRepository choiceRepository;
	
	@Autowired
	MultipleChoiceExamQuestionRepository repository;
	
	@Autowired
	BaseExamQuestionRepository baseRepository;
	
	@Autowired
	ExamRepository examRepository;
	
	@GetMapping("/api/choice")
	public List<MultipleChoiceExamQuestion> findAllMultipleChoiceExamQuestions() {
		return (List<MultipleChoiceExamQuestion>) repository.findAll();
	}
	
	@GetMapping("/api/exam/{eId}/choice")
	public List<MultipleChoiceExamQuestion> findMultipleChoiceForExam(@PathVariable("eId") int id) {
		Optional<Exam> data = examRepository.findById(id);
		List<MultipleChoiceExamQuestion> list = new ArrayList<>();
		if(data.isPresent()) {
			Exam exam = data.get();
			for(BaseExamQuestion q : exam.getBaseExamQuestions()) {
				if(q instanceof MultipleChoiceExamQuestion) {
					list.add((MultipleChoiceExamQuestion) q);
				}
			}
		}
		
		return list;
	}
	
	@PostMapping("/api/exam/{eId}/choice")
	public MultipleChoiceExamQuestion createForExam(@PathVariable("eId") int id, @RequestBody MultipleChoiceExamQuestion question) {
		Optional<Exam> data = examRepository.findById(id);
		if(data.isPresent()) {
			Exam exam = data.get();
			question.setExam(exam);
			System.out.println(exam.getTitle() + " " + question.getTitle());
			repository.save(question);
		}
		return question;
	}
	
//	@PutMapping("/api/choice/{cId}")
//	public MultipleChoiceExamQuestion updateById(@PathVariable("cId") int id, @RequestBody MultipleChoiceExamQuestion question) {
//		Optional<MultipleChoiceExamQuestion> data = repository.findById(id);
//		if(data.isPresent()) {
//			MultipleChoiceExamQuestion old = data.get();
//			repository.deleteById(old.getId());
//			question.setExam(old.getExam());
//			repository.save(question);
//			for(Choice c : question.getChoices()) {
//				c.setQuestion(question);
//				choiceRepository.save(c);
//				
//			}
//
//		}
//		return question;
//	}
	
	@PutMapping("/api/choice/{cId}")
	public MultipleChoiceExamQuestion updateById(@PathVariable("cId") int id, @RequestBody MultipleChoiceExamQuestion question) {
		Optional<MultipleChoiceExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			MultipleChoiceExamQuestion old = data.get();
			old.setTitle(question.getTitle());
			old.setDescription(question.getDescription());
			old.setPoints(question.getPoints());
			old.setSubtitle(question.getSubtitle());
			old.setRightChoiceName(question.getRightChoiceName());
			repository.save(old);
			for(Choice c : old.getChoices()) {
				choiceRepository.deleteById(c.getId());
			}
			for(Choice c : question.getChoices()) {
				c.setQuestion(old);
				choiceRepository.save(c);
			}
		}
		return question;
	}
	
	@DeleteMapping("/api/choice/{cId}")
	public void deleteById(@PathVariable("cId") int id) {
		repository.deleteById(id);
	}
}
