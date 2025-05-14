package com.ABC_company.api.service;

import com.ABC_company.api.entity.Manager;
import com.ABC_company.api.repo.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ManagerDetailsServiceImp implements UserDetailsService {
    @Autowired
    private ManagerRepository managerRepository;
    @Override
    public UserDetails loadUserByUsername(String manName) throws UsernameNotFoundException {
        Manager manager = managerRepository.findByManagerName(manName);
        if(manager != null){
            return User.builder() //org.springframework.security.core.userdetails.User.builder()
                    .username(manager.getManagerName())
                    .password(manager.getPassword())
                    .roles(manager.getRoles().toArray(new String[0]))
                    .build();
        }throw new UsernameNotFoundException("Manager named "+manName+" is not found.");

    }
}
