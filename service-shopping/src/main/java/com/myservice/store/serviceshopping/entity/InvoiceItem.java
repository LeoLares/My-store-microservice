package com.myservice.store.serviceshopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

import lombok.Data;

@Entity 
@Data
@Table(name=("tbl_invoice_items"))
public class InvoiceItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "El stock debe ser mayor a cero")
    private Double quantity;
    private Double  price;

    @Column(name = "product_id")
    private Long productId;
    
    @Transient //esto significa que no va a ser registrado en nuestra base de datos
    private Double subTotal; //aquie guardaremos el subtotal


    public Double getSubtotal(){
        if(this.price>0 && this.quantity > 0){
            return this.quantity * this.price;

        }else{
            return(double) 0;
        }

    }

    public InvoiceItem(){
        this.quantity=(double)0;
        this.price = (double)0;
    }
}
