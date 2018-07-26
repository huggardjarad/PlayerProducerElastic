package org.qa.userproducerapp.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Random;

@Document(indexName = "user_index", type = "user")
public class User {

    @Id
    private Long id;
    private String name;
    private String email;
    private Long seedNumber;
    private String imageData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long generateSeed(){
         seedNumber = (long) new Random().nextInt(2000);
        return seedNumber;
    }

    public Long getSeedNumber() { return seedNumber; }

    public void setSeedNumber(Long seedNumber) { this.seedNumber = seedNumber; }

    public String getImageData() { return imageData; }

    public void setImageData(String imageData) { this.imageData = imageData; }

    @Override
    public String toString() {
        return "User: " + "" +
                " id: " + id +
                " name: " + name +
                " email: " + email +
                " seedNumber: " + seedNumber +
                " imageData: " + imageData;
    }
}
