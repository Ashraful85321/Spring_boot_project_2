package com.ABC_company.api.controller;

import com.ABC_company.api.entity.Manager;
import com.ABC_company.api.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping
    public List<Manager> getAllManager(){
        return managerService.getManager();
    }
    @PostMapping
    public void addAManager(@RequestBody Manager manager){
         managerService.addManager(manager);
    }
    @PutMapping("/{mName}")
    public String updateAManager(@RequestBody Manager manager, @PathVariable String mName){
        return managerService.updateManager(mName, manager);
    }
}