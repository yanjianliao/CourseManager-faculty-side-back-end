package webdev.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Exam extends Widget{

	private String title;
	private String description;
	private String points;
	@OneToMany(mappedBy="exam", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<BaseExamQuestion> baseExamQuestions;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<BaseExamQuestion> getBaseExamQuestions() {
		return baseExamQuestions;
	}
	public void setBaseExamQuestions(List<BaseExamQuestion> baseExamQuestions) {
		this.baseExamQuestions = baseExamQuestions;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	
}
