package com.ifdeveloper.service;

import com.ifdeveloper.model.Voucher;
import com.ifdeveloper.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;


    public Voucher getVoucher(String id) {
        return voucherRepository.findById(id).get();
    }

    public List<Voucher> getVouchers() {
        return voucherRepository.findAll();
    }

    public void addVoucher(Voucher voucher) {
        voucherRepository.save(voucher);
    }

}
