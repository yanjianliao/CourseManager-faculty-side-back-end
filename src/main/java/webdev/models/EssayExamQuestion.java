package webdev.models;

import javax.persistence.Entity;

@Entity
public class EssayExamQuestion extends BaseExamQuestion{
	
	String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
