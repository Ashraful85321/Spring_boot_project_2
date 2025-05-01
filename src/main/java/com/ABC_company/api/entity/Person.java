package com.ABC_company.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//@Document(collation = "person")  --> Just mistaking 'a' with 'e' made me suffer a lot.
@Document(collection = "person")
@Data
@NoArgsConstructor
public class Person {
    @Id
    private ObjectId id;
    private String name;
    private String details;
    private LocalDateTime date;

}
