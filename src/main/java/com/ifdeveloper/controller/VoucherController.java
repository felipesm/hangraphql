package com.ifdeveloper.controller;

import com.ifdeveloper.model.Voucher;
import com.ifdeveloper.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1")
@Controller
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @SchemaMapping(typeName = "Query", value = "voucher")
    public Voucher voucher(@Argument String id) {
        return voucherService.getVoucher(id);
    }

    @SchemaMapping(typeName = "Query", value = "vouchers")
    public List<Voucher> vouchers() {
        return voucherService.getVouchers();
    }

    @MutationMapping
    public Voucher addVoucher(@Argument String id, @Argument Float percentage, @Argument String expire) {
        var voucher = new Voucher(id, percentage, expire);
        return voucherService.addVoucher(voucher);
    }

    @GetMapping("/vouchers")
    public ResponseEntity<List<Voucher>> getVouchers() {
        var vouchers = voucherService.getVouchers();
        return ResponseEntity.ok(vouchers);
    }

    @PostMapping("/voucher")
    public ResponseEntity addVoucherAPI(@RequestBody Voucher voucher) {
        voucherService.addVoucher(voucher);
        return ResponseEntity.ok().build();
    }

}
