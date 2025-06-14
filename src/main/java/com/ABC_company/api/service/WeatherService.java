package com.ABC_company.api.service;

import com.ABC_company.api.cache.AppCache;
import com.ABC_company.api.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;
//    private static final String API = "http://api.weatherstack.com/current?access_key=Api_Key&query=City";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;
    public WeatherResponse getWeather(String city){
        String finalApi = appCache.cacheData.get("weather_api").replace("<apiKey>", apiKey).replace("<city>", city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }

}
