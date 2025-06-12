package com.ABC_company.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private Current current;
    @Getter
    @Setter
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;
        @JsonProperty("wind_dir")
        private String windDirection;
        private int pressure;
        private int humidity;
        @JsonProperty("feelslike")
        private int feelsLike;
    }

}
