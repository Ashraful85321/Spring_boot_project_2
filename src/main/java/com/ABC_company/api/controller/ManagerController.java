package com.ABC_company.api.controller;

import com.ABC_company.api.entity.Manager;
import com.ABC_company.api.service.ManagerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping
    public List<Manager> getAllManager(){
        return managerService.getManager();
    }

    @GetMapping("/{mId}")
    public Optional<Manager> getTheManager(@PathVariable ObjectId mId){
        return managerService.getAManager(mId);
    }
    @PostMapping
    public void addAManager(@RequestBody Manager manager){
         managerService.addManager(manager);
    }
    @PutMapping("/{mName}")
    public String updateAManager(@RequestBody Manager manager, @PathVariable String mName){
        return managerService.updateManager(mName, manager);
    }
    @DeleteMapping("/{mName}")
    public String deleteManager(@PathVariable String mName){
        managerService.deleteManager(mName);
        return "Deleted";
    }
}