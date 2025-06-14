package com.ABC_company.api.cache;

import com.ABC_company.api.entity.AppConfiguration;
import com.ABC_company.api.repo.AppConfigRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    private AppConfigRepository appConfigRepository;
    public Map<String, String> cacheData;

    @PostConstruct
    public void init(){
        cacheData = new HashMap<>();
        List<AppConfiguration> all = appConfigRepository.findAll();
        all.forEach(x-> cacheData.put(x.getKey(), x.getValue()));
    }
}
