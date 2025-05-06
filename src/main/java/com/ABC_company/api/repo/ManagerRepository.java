package com.ABC_company.api.repo;

import com.ABC_company.api.entity.Manager;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ManagerRepository extends MongoRepository<Manager, ObjectId> {
    Manager findByManagerName(String managerName);
    void deleteByManagerName(String managerName);
}
