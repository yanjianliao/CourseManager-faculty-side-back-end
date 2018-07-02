package webdev.models;

import javax.persistence.Entity;

@Entity
public class TrueOrFalseExamQuestion extends BaseExamQuestion{

	private String trueOrFalse;

	private String description;
	public String getTrueOrFalse() {
		return trueOrFalse;
	}
	public void setTrueOrFalse(String trueOrFalse) {
		this.trueOrFalse = trueOrFalse;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
