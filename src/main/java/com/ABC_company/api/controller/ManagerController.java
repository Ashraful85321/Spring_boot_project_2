package com.ABC_company.api.controller;

import com.ABC_company.api.entity.Manager;
import com.ABC_company.api.entity.WeatherResponse;
import com.ABC_company.api.service.ManagerService;
import com.ABC_company.api.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
@Slf4j
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public List<Manager> getAllManager(){
//        return managerService.getManager();
//    }

//    @GetMapping("/{mId}")
//    public Optional<Manager> getTheManager(@PathVariable ObjectId mId){
//        return managerService.getAManager(mId);
//    }
    @PostMapping
    public ResponseEntity<Void> addAManager(@RequestBody Manager manager){
        try {
            managerService.addManager2(manager);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error("Error occurred saving manager {} because {} ",manager.getManagerName(), e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

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

    @GetMapping
    public ResponseEntity<String> greetings(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String mName = auth.getName();
        WeatherResponse weather = weatherService.getWeather("Dhaka");
        String msg = "";
        if(weather != null){
            msg = "The temperature is " + weather.getCurrent().getTemperature() + " feels like " + weather.getCurrent().getFeelsLike();
        }
//        return new ResponseEntity<>("Hello " + mName + msg, HttpStatus.OK);
        return ResponseEntity.ok("Hello " + mName + msg);
    }
}