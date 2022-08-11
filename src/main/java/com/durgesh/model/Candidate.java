package com.durgesh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candidates")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    @Column(name = "first_name")
    private String firstName;
     
    @Column(name = "last_name")
    private String lastName;
     
    @Column(name = "profile_picture")
    private String profilePicture;
     
    @Column(name = "photo_id")
    private String photoId;
     
    @Column(name = "document")
    private String document;
 
    // getters & setters are not shown 
     
}