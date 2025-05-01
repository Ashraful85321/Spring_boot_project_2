package com.ABC_company.api.service;

import com.ABC_company.api.entity.Manager;
import com.ABC_company.api.repo.ManagerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    public void addManager(Manager manager){
        managerRepository.save(manager);
    }
    public List<Manager> getManager(){
        return managerRepository.findAll();
    }
    public Optional<Manager> getAManager(ObjectId pId){
        return managerRepository.findById(pId);
    }
    public Manager findByMName(String mName){
        return managerRepository.findByManagerName(mName);
    }
    public String updateManager(String mName, Manager newManager){
        Manager currentManager = this.findByMName(mName);
        if(currentManager != null){
            currentManager.setManagerName(newManager.getManagerName());
            currentManager.setPassword(newManager.getPassword());
            managerRepository.save(currentManager);
        }
          return "Saved and done.";
    }
    public void deleteManager(ObjectId pId){
        managerRepository.deleteById(pId);
    }
}
