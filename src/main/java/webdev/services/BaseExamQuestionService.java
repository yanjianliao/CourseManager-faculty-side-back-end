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

import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
import webdev.repositories.BaseExamQuestionRepository;
import webdev.repositories.ExamRepository;

@RestController
@CrossOrigin(origins = "*")
public class BaseExamQuestionService {
	
	@Autowired
	BaseExamQuestionRepository repository;
	
	@Autowired
	ExamRepository examRepository;
	
	@GetMapping("/api/questions")
	public List<BaseExamQuestion> findAllQuestions() {
		return (List<BaseExamQuestion>) repository.findAll();
	}
	
	@GetMapping("/api/questions/{qId}")
	public BaseExamQuestion findQuestionsById(@PathVariable("qId") int id) {
		Optional<BaseExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return new BaseExamQuestion();
	}
	
	@GetMapping("/api/exam/{eId}/questions")
	public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("eId")int id) {
		Optional<Exam> data = examRepository.findById(id);
		if(data.isPresent()) {
			Exam exam = data.get();
			return exam.getBaseExamQuestions();
		}
		
		return new ArrayList<>();
	}
	
	@PostMapping("/api/exam/{eId}/question")
	public BaseExamQuestion createQuestionForExam(@PathVariable("eId")int id, @RequestBody BaseExamQuestion question) {
		Optional<Exam> data = examRepository.findById(id);
		if(data.isPresent()) {
			Exam exam = data.get();
			question.setExam(exam);
			repository.save(question);
		}
		
		return question;
	}
	
	@DeleteMapping("/api/question/{qId}")
	public void deleteQuestionById(@PathVariable("qId") int id) {
		repository.deleteById(id);
	}
	
}
