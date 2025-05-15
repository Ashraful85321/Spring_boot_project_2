package com.ABC_company.api.controller;

import com.ABC_company.api.customExceptions.PersonNotFoundException;
import com.ABC_company.api.entity.Person;
import com.ABC_company.api.service.ManagerService;
import com.ABC_company.api.service.PersonService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping
    public ResponseEntity<?> getAllSupervised(){
//        return new ArrayList<>(thePerson.values());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String man = auth.getName();
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String mName = auth.getName();
        Optional<Person> theResponse = service.getAPerson(personId, mName);
        if(theResponse.isPresent()){
            return new ResponseEntity<>(theResponse.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
//        thePerson.put(person.getId(), person);
         try {
             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
             String man = auth.getName();
             service.addPerson(person, man);
             return new ResponseEntity<>(person,HttpStatus.CREATED);
         }catch (Exception e){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }

    }
    @PutMapping("/id/{personId}")
    public ResponseEntity<?> updatePerson(@PathVariable ObjectId personId, @RequestBody Person person){
//        thePerson.put(personId, person);
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String mName = auth.getName();
            service.updatePerson(personId, person, mName);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (PersonNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/id/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable ObjectId personId){
//        thePerson.remove(personId);
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String mName = auth.getName();
            service.deletePerson(personId, mName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}