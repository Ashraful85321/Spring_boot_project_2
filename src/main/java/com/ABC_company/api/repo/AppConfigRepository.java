package com.ABC_company.api.repo;

import com.ABC_company.api.entity.AppConfiguration;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AppConfigRepository extends MongoRepository<AppConfiguration, ObjectId> {
}
