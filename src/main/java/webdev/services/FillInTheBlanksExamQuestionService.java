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
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.models.Variable;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksExamQuestionRepository;
import webdev.repositories.VariableRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FillInTheBlanksExamQuestionService {
	
	@Autowired
	FillInTheBlanksExamQuestionRepository repository;
	
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	VariableRepository variableRepository;
	
	@GetMapping("/api/blanks")
	public List<FillInTheBlanksExamQuestion> findAllMultipleChoiceExamQuestions() {
		return (List<FillInTheBlanksExamQuestion>) repository.findAll();
	}
	
	@GetMapping("/api/exam/{eId}/blanks")
	public List<FillInTheBlanksExamQuestion> findEssayExamQuestionForExam(@PathVariable("eId") int id) {
		Optional<Exam> data = examRepository.findById(id);
		List<FillInTheBlanksExamQuestion> list = new ArrayList<>();
		if(data.isPresent()) {
			Exam exam = data.get();
			for(BaseExamQuestion q : exam.getBaseExamQuestions()) {
				if(q instanceof FillInTheBlanksExamQuestion) {
					list.add((FillInTheBlanksExamQuestion) q);
				}
			}
		}
		return list;
	}
	
	@PostMapping("/api/exam/{eId}/blanks")
	public FillInTheBlanksExamQuestion createForExam(@PathVariable("eId") int id, @RequestBody FillInTheBlanksExamQuestion question) {
		Optional<Exam> data = examRepository.findById(id);
		if(data.isPresent()) {
			Exam exam = data.get();
			question.setExam(exam);
			repository.save(question);
		}
		return question;
	}
	
	@PutMapping("/api/blanks/{eId}")
	public FillInTheBlanksExamQuestion updateById(@PathVariable("eId") int id, @RequestBody FillInTheBlanksExamQuestion question) {
		Optional<FillInTheBlanksExamQuestion> data = repository.findById(id);
		if(data.isPresent()) {
			FillInTheBlanksExamQuestion old = data.get();
			old.setTitle(question.getTitle());
			old.setDescription(question.getDescription());
			old.setPoints(question.getPoints());
			old.setSubtitle(question.getSubtitle());
			repository.save(old);
			for(Variable v : old.getVariables()) {
				variableRepository.deleteById(v.getId());
			}
			
			for(Variable v : question.getVariables()) {
				v.setQuestion(old);
				variableRepository.save(v);
			}
		}
		return question;
	}
	
	
	@DeleteMapping("/api/blanks/{bId}")
	public void deleteById(@PathVariable("bId") int id) {
		repository.deleteById(id);
	}
}
