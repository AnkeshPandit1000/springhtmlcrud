package com.bmt.spring_crud.dto;
import java.util.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.*;

import javax.xml.crypto.Data;

public class ClientDto {

    private int id;
    private Date createdAt;

    @NotEmpty(message = "The First name is required")
    private String firstname;

    @NotEmpty(message = "The Last name is Required")
    private String lastname;

    @NotEmpty(message = "The Email field is required")
    @Email
    private String email;

    private String address;
    private String phone;

    @NotEmpty(message = "The Status fields is Required")
    private String status;

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
