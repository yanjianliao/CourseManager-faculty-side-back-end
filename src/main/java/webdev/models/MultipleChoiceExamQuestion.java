package webdev.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class MultipleChoiceExamQuestion extends BaseExamQuestion {
	// how many points, a title, a description, a list of choices, and a right choice.
	
	private String description;
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Choice> choices;
	private String rightChoiceName;
	
	public String getRightChoiceName() {
		return rightChoiceName;
	}
	public void setRightChoiceName(String rightChoiceName) {
		this.rightChoiceName = rightChoiceName;
	}
	public List<Choice> getChoices() {
		return choices;
	}
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}




}
