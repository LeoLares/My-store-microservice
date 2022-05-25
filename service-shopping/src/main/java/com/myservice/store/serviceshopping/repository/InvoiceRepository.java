package com.myservice.store.serviceshopping.repository;

import java.util.List;

import com.myservice.store.serviceshopping.entity.Invoice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public List<Invoice> findByCustomerId(Long customerId);//buscamos por el cliente
    public Invoice findByNumberInvoice(String numberInvoice);//buscamos por numero de factura
}
