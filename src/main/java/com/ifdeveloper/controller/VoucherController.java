package com.ifdeveloper.controller;

import com.ifdeveloper.model.Voucher;
import com.ifdeveloper.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @SchemaMapping(typeName = "Query", value = "voucher")
    public Voucher voucher() {
        return voucherService.voucher();
    }

    @SchemaMapping(typeName = "Query", value = "vouchers")
    public List<Voucher> vouchers() {
        return voucherService.vouchers();
    }

}
