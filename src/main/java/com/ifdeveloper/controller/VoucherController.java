package com.ifdeveloper.controller;

import com.ifdeveloper.model.Voucher;
import com.ifdeveloper.model.input.VoucherInput;
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

import java.util.List;

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

    @SchemaMapping(typeName = "Query", value = "vouchersByPercentage")
    public List<Voucher> vouchersByPercentage(@Argument Float minPercentage)  {
        return voucherService.getVouchersByPercentage(minPercentage);
    }

    @MutationMapping
    public Voucher addVoucher(@Argument VoucherInput voucherInput) {
        var voucher = new Voucher(voucherInput.getId(), voucherInput.getPercentage(), voucherInput.getExpire());
        return voucherService.addVoucher(voucher);
    }

    @MutationMapping
    public Boolean deleteVoucher(@Argument String id) {
        var voucher = voucherService.getVoucher(id);
        if (voucher != null) {
            voucherService.deleteVoucher(voucher);
            return true;
        }
        return false;
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
