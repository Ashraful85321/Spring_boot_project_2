package com.ABC_company.api.repo;

import com.ABC_company.api.entity.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PersonRepository extends MongoRepository<Person, ObjectId> {
}
