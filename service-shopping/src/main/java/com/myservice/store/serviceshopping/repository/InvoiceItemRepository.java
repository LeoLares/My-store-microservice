package com.myservice.store.serviceshopping.repository;

import com.myservice.store.serviceshopping.entity.InvoiceItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem,Long> {
    
}
