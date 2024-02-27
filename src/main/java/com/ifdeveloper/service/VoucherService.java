package com.ifdeveloper.service;

import com.ifdeveloper.model.Voucher;
import com.ifdeveloper.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    public Voucher voucher() {
        return new Voucher(
                getId(),
                getValue(),
                getExpire());
    }

    public List<Voucher> vouchers() {
        var vouchers = new ArrayList<Voucher>();

        vouchers.add(new Voucher(getId(), 12F, getExpire()));
        vouchers.add(new Voucher(getId(), 15F, getExpire()));
        vouchers.add(new Voucher(getId(), 20F, getExpire()));

        return vouchers;
    }

    public List<Voucher> getVouchers() {
        return voucherRepository.findAll();
    }

    public void add(Voucher voucher) {
        voucherRepository.save(voucher);
    }

    private String getId() {
        return UUID.randomUUID().toString();
    }

    private Float getValue() {
        return 15F;
    }

    private String getExpire() {
        return LocalDate.now().toString();
    }


}
