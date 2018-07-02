package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.EssayExamQuestion;
import webdev.models.FillInTheBlanksExamQuestion;

public interface FillInTheBlanksExamQuestionRepository extends CrudRepository<FillInTheBlanksExamQuestion, Integer>{

}
