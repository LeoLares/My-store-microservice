package com.myservice.store.serviceclient.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="tbl_customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nro de dni no puede estar vacio")
    @Size(min = 8, max = 8, message = "El tamaño del numero de dni es 8")//con este ponemos un minimo y maximo de caracteres
    @Column(name = "number_id", unique = true, length = 8, nullable = false)
    private String numberID;

    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(name = "first_name",nullable = false)
    private String firstName;


    @NotEmpty(message = "El Apellido no puede ser vacio") 
    @Column(name = "last_name",nullable = false)
    private String lastName;


    @NotEmpty(message = "El email no puede ser vacio") 
    @Email(message = "El correo no esta bien ingresado")
    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @NotNull(message = "La region no puede estar vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Region region;
    
    private String state;
}
