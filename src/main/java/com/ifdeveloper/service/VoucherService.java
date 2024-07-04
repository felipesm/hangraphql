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
        return voucherRepository.findById(id).orElse(null);
    }

    public List<Voucher> getVouchers() {
        return voucherRepository.findAll();
    }

    public List<Voucher> getVouchersByPercentage(Float minPercentage) {
        return voucherRepository.findByPercentageGreaterThan(minPercentage);
    }

    public Voucher addVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    public void deleteVoucher(Voucher voucher) {
        voucherRepository.delete(voucher);
    }

}
