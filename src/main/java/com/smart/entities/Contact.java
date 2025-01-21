package com.smart.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int cId;
    private String phone;
    private String name;
    private String nickName;
    private String work;
    private String email;
    private String image;
    @Column(length=500)
    private String description;

    @ManyToOne//table is bidirectional, many contacts is related to one user
    
    private User user;

   

    public Contact() {
        super();
    }

    public int getCId() {
        return this.cId;
    }

    public void setCId(int cId) {
        this.cId = cId;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWork() {
        return this.work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
