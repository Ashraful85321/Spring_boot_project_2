package com.ABC_company.api.service;

import com.ABC_company.api.entity.Person;
import com.ABC_company.api.repo.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public void addPerson(Person person){
        personRepository.save(person);
    }
    public List<Person> getPersons(){
        return personRepository.findAll();
    }
    public Optional<Person> getAPerson(ObjectId pId){
        return personRepository.findById(pId);
    }
    public String updatePerson(ObjectId pId, Person newPerson){
        Person currentPerson = personRepository.findById(pId).orElse(null);
        if(currentPerson != null){
            currentPerson.setName(newPerson.getName() != null && !(newPerson.getName().equals(currentPerson.getName()))? newPerson.getName() : currentPerson.getName());
            currentPerson.setDetails(newPerson.getDetails()!=null && !(newPerson.getDetails().equals(currentPerson.getDetails()))?newPerson.getDetails() : currentPerson.getDetails());
        }
        personRepository.save(currentPerson);
          return "Done and done.";
    }
    public void deletePerson(ObjectId pId){
        personRepository.deleteById(pId);
    }
}
