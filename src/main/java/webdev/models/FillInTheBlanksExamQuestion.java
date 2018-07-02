package webdev.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class FillInTheBlanksExamQuestion extends BaseExamQuestion{

	String description;
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, orphanRemoval = true)
	List<Variable> variables;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Variable> getVariables() {
		return variables;
	}
	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}
	
}
