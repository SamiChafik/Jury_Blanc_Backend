package com.example.deliverymatch.entities;

import jakarta.persistence.Entity;

@Entity
public class Driver extends User{
    Boolean verified;
}
