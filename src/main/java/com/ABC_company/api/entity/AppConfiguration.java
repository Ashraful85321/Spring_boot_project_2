package com.ABC_company.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "app_configuration")
@Data
@NoArgsConstructor
public class AppConfiguration {

    private String key;
    private String value;

}
