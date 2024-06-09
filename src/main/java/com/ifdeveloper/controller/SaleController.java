package com.ifdeveloper.controller;

import com.ifdeveloper.model.Sale;
import com.ifdeveloper.model.input.SaleInput;
import com.ifdeveloper.service.SaleService;
import com.ifdeveloper.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private VoucherService voucherService;

    @SchemaMapping(typeName = "Query", value = "sale")
    public Sale sale(@Argument String id) {
        return saleService.getSale(id);
    }

    @SchemaMapping(typeName = "Query", value = "sales")
    public List<Sale> sales() {
        return saleService.getSales();
    }

    @MutationMapping
    public Sale addSale(@Argument SaleInput saleInput) {
        var voucher = voucherService.getVoucher(saleInput.getVoucherId());
        var sale = new Sale(saleInput.getId(), saleInput.getDate(), saleInput.getTotal(), saleInput.getStatus(), voucher);
        return saleService.addSale(sale);
    }

    @MutationMapping
    public Boolean deleteSale(@Argument String id) {
        var sale = saleService.getSale(id);
        if (sale != null) {
            saleService.deleteSale(sale);
            return true;
        }
        return false;
    }

}
