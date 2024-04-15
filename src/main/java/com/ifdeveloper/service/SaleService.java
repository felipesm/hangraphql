package com.ifdeveloper.service;

import com.ifdeveloper.model.Sale;
import com.ifdeveloper.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;


    public Sale getSale(String id) {
        return saleRepository.findById(id).orElse(null);
    }

    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    public Sale addSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public void deleteSale(Sale sale) {
        saleRepository.delete(sale);
    }

}
