package com.ABC_company.api.service;

import com.ABC_company.api.entity.Manager;
import com.ABC_company.api.entity.Person;
import com.ABC_company.api.repo.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ManagerService mService;

    @Transactional
    public void addPerson(Person person, String man){
        //Add a new person
        person.setDate(LocalDateTime.now());
        Person savedPerson = personRepository.save(person);
        //Then add reference to manager
        Manager manager = mService.findByMName(man);
        manager.getSupervisedPerson().add(savedPerson);
        mService.addManager(manager);
    }
    public List<Person> getPersons(String man){
        Manager manager = mService.findByMName(man);
        return manager.getSupervisedPerson(); //I got confused but remembered about Lombok
    }
    public Optional<Person> getAPerson(ObjectId pId){
        return personRepository.findById(pId);
    }
    public void updatePerson(ObjectId pId, Person newPerson){
        Person currentPerson = personRepository.findById(pId).orElse(null);
        if(currentPerson != null){
            currentPerson.setName(newPerson.getName() != null && !(newPerson.getName().equals(currentPerson.getName()))? newPerson.getName() : currentPerson.getName());
            currentPerson.setDetails(newPerson.getDetails()!=null && !(newPerson.getDetails().equals(currentPerson.getDetails()))?newPerson.getDetails() : currentPerson.getDetails());
            personRepository.save(currentPerson);
        }
    }
    @Transactional
    public void deletePerson(ObjectId pId, String man){
        //Remove person reference from manager
        Manager manager = mService.findByMName(man);
        manager.getSupervisedPerson().removeIf(thePerson -> thePerson.getId().equals(pId));
        mService.addManager(manager);
        //Then delete the person
        personRepository.deleteById(pId);
    }
}
