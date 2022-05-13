package com.myservice.store.serviceshopping.service;

import java.util.List;

import com.myservice.store.serviceshopping.entity.Invoice;
import com.myservice.store.serviceshopping.repository.InvoiceItemRepository;
import com.myservice.store.serviceshopping.repository.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;
    
    @Autowired
    InvoiceItemRepository invoiceItemsRepository;

    @Override
    public List<Invoice> findInvoiceAll() {
         return  invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice ( invoice.getNumberInvoice () );
        if(invoiceDB != null){
            return invoiceDB;
        }
        invoice.setState("CREATED");
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if(invoiceDB == null){
            return null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {
        
        return invoiceRepository.findById(id).orElse(null);
    }
    
}
