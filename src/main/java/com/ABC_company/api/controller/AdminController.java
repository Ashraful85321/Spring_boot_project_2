package com.ABC_company.api.controller;

import com.ABC_company.api.entity.Manager;
import com.ABC_company.api.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired private ManagerService mService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
//        List<Manager> manager = mService.getManager().stream().filter(x -> !x.getRoles().contains("ADMIN")).collect(Collectors.toList());
        List<Manager> manager = mService.getManager();
        if(!manager.isEmpty()){
            return new ResponseEntity<>(manager, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody Manager manager){
        try {
            mService.addAdmin(manager);
            return new ResponseEntity<>(manager, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
