package webdev.models;

import javax.persistence.Entity;

@Entity
public class TrueOrFalseExamQuestion extends BaseExamQuestion{

	private String trueOrFlase;
	private String description;
	
	public String getTrueOrFlase() {
		return trueOrFlase;
	}
	public void setTrueOrFlase(String trueOrFlase) {
		this.trueOrFlase = trueOrFlase;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
