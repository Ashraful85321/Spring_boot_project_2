package com.ABC_company.api.controller;

import com.ABC_company.api.entity.Person;
import com.ABC_company.api.service.ManagerService;
import com.ABC_company.api.service.PersonService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class MyController {
//    private Map<Long, Person> thePerson = new HashMap<>();
@Autowired
private PersonService service;
@Autowired
private ManagerService mService;
    @GetMapping("/{man}")
    public ResponseEntity<?> getAllSupervised(@PathVariable String man){
//        return new ArrayList<>(thePerson.values());
        List<Person> allPerson = service.getPersons(man);
        if(allPerson != null && !allPerson.isEmpty()){
            return new ResponseEntity<>(allPerson, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{personId}")
    public ResponseEntity<Person> getThePerson(@PathVariable ObjectId personId){
//        return thePerson.get(personId);
//        return service.getAPerson(personId).orElse(null);
        Optional<Person> theResponse = service.getAPerson(personId);
        if(theResponse.isPresent()){
            return new ResponseEntity<>(theResponse.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/{man}")
    public ResponseEntity<Person> createPerson(@RequestBody Person person, @PathVariable String man){
//        thePerson.put(person.getId(), person);
         try {
             service.addPerson(person, man);
             return new ResponseEntity<>(person,HttpStatus.CREATED);
         }catch (Exception e){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }

    }
    @PutMapping("/id/{personId}")
    public ResponseEntity<Person> updatePerson(@PathVariable ObjectId personId, @RequestBody Person person){
//        thePerson.put(personId, person);
        try {
            service.updatePerson(personId, person);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/id/{personId}/{man}")
    public ResponseEntity<Person> deletePerson(@PathVariable ObjectId personId, @PathVariable String man){
//        thePerson.remove(personId);
        try {
            service.deletePerson(personId, man);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}