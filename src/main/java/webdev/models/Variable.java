package webdev.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Variable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@JsonIgnore
	@ManyToOne
	FillInTheBlanksExamQuestion question;
	
	String variable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FillInTheBlanksExamQuestion getQuestion() {
		return question;
	}

	public void setQuestion(FillInTheBlanksExamQuestion question) {
		this.question = question;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}
	
	
}
