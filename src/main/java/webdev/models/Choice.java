package webdev.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Choice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;

	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY)
//	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	private MultipleChoiceExamQuestion question;
	
	public MultipleChoiceExamQuestion getQuestion() {
		return question;
	}
	public void setQuestion(MultipleChoiceExamQuestion question) {
		this.question = question;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
