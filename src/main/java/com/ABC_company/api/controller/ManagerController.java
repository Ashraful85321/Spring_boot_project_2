package com.ABC_company.api.controller;

import com.ABC_company.api.entity.Manager;
import com.ABC_company.api.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

//    @GetMapping
//    public List<Manager> getAllManager(){
//        return managerService.getManager();
//    }

//    @GetMapping("/{mId}")
//    public Optional<Manager> getTheManager(@PathVariable ObjectId mId){
//        return managerService.getAManager(mId);
//    }
    @PostMapping
    public void addAManager(@RequestBody Manager manager){
         managerService.addManager2(manager);
    }
    @PutMapping
    public String updateAManager(@RequestBody Manager manager){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String mName = auth.getName();
        return managerService.updateManager(mName, manager);
    }
    @DeleteMapping
    public String deleteManager(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String mName = auth.getName();
        managerService.deleteManager(mName);
        return "Deleted";
    }
}