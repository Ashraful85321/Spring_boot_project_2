package com.ABC_company.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "manager")
@Data
@NoArgsConstructor
public class Manager {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String managerName;
    @NonNull
    private String password;
    @DBRef
    private List<Person> supervisedPerson = new ArrayList<>();
    private List<String> roles;

}
