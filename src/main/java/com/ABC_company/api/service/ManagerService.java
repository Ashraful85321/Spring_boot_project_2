package com.ABC_company.api.service;

import com.ABC_company.api.entity.Manager;
import com.ABC_company.api.repo.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ManagerService {
    private static final String APP_PACKAGE = "com.ABC_company.api";
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addManager(Manager manager){
        try {
            managerRepository.save(manager);
        }catch (Exception e){
            log.error("Error saving manager {}",manager.getManagerName());
        }

    }
    public void addManager2(Manager manager){
        try {
            manager.setPassword(passwordEncoder.encode(manager.getPassword()));
            manager.setRoles(Arrays.asList("USER"));
            managerRepository.save(manager);
        }catch (Exception e){
            StackTraceElement origin = Arrays.stream(e.getStackTrace())
                    .filter(el -> el.getClassName().startsWith(APP_PACKAGE))
                    .findFirst()
                    .orElse(e.getStackTrace()[0]);
            log.error("Error occurred at : {} : {} : {} : {}",
                    origin.getFileName(),
                    origin.getClassName(),
                    origin.getMethodName(),
                    e.getMessage() );
            throw e;
        }
    }

    public void addAdmin(Manager manager){
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        manager.setRoles(Arrays.asList("USER", "ADMIN"));
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
        currentManager.setManagerName(newManager.getManagerName());
        currentManager.setPassword(passwordEncoder.encode(newManager.getPassword()));
        currentManager.setRoles(Arrays.asList("USER"));
        managerRepository.save(currentManager);
        return "Saved and done.";

    }
    public void deleteManager(ObjectId pId){
        managerRepository.deleteById(pId);
    }
    public void deleteManager(String mName){
        Manager manager = this.findByMName(mName);
        managerRepository.deleteByManagerName(mName);
    }
}
