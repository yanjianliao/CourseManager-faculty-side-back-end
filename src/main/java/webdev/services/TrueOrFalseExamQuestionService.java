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
import webdev.models.Exam;
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.TrueOrFalseExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class TrueOrFalseExamQuestionService {

	@Autowired
	TrueOrFalseExamQuestionRepository repository;
	
	@Autowired
	ExamRepository examRepository;
	
	
	@GetMapping("/api/truefalse")
	public List<TrueOrFalseExamQuestion> findAllQuestions() {
		return (List<TrueOrFalseExamQuestion>) repository.findAll();
	}
	
	@GetMapping("/api/exam/{eId}/truefalse")
	public List<TrueOrFalseExamQuestion> findEssayExamQuestionForExam(@PathVariable("eId") int id) {
		Optional<Exam> data = examRepository.findById(id);
		List<TrueOrFalseExamQuestion> list = new ArrayList<>();
		if(data.isPresent()) {
			Exam exam = data.get();
			for(BaseExamQuestion q : exam.getBaseExamQuestions()) {
				if(q instanceof TrueOrFalseExamQuestion) {
					list.add((TrueOrFalseExamQuestion) q);
				}
			}
		}
		return list;
	}
	
	@PostMapping("/api/exam/{eId}/truefalse")
	public TrueOrFalseExamQuestion createForExam(@PathVariable("eId") int id, @RequestBody TrueOrFalseExamQuestion question) {
		Optional<Exam> data = examRepository.findById(id);
		if(data.isPresent()) {
			Exam exam = data.get();
			question.setExam(exam);
			repository.save(question);
		}
		return question;
	}
	
	@PutMapping("/api/truefalse/{eId}")
	public TrueOrFalseExamQuestion updateById(@PathVariable("eId") int id, @RequestBody TrueOrFalseExamQuestion question) {
		Optional<TrueOrFalseExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			TrueOrFalseExamQuestion old = data.get();
			old.setTitle(question.getTitle());
			old.setDescription(question.getDescription());
			old.setPoints(question.getPoints());
			old.setSubtitle(question.getSubtitle());
			old.setTrueOrFalse(question.getTrueOrFalse());
			repository.save(old);
		}
		return question;
	}
	
	
	@DeleteMapping("/api/truefalse/{cId}")
	public void deleteById(@PathVariable("cId") int id) {
		repository.deleteById(id);
	}
	
}
