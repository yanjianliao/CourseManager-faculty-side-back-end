package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.Choice;

public interface ChoiceRepository  extends CrudRepository<Choice, Integer>{

}
