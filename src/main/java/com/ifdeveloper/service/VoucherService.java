package com.ifdeveloper.service;

import com.ifdeveloper.exception.VoucherException;
import com.ifdeveloper.exception.VoucherNotFoundException;
import com.ifdeveloper.model.Voucher;
import com.ifdeveloper.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;


    public Voucher getVoucher(String id) {
        var voucher = voucherRepository.findById(id).orElse(null);
        if (Objects.isNull(voucher)) {
            throw new VoucherNotFoundException(String.format("Voucher with id %s not found", id));
        }
        return voucherRepository.findById(id).orElse(null);
    }

    public List<Voucher> getVouchers(Pageable pageable) {
        return voucherRepository.findAll(pageable).getContent();
    }

    public List<Voucher> getVouchersByPercentage(Float minPercentage) {
        return voucherRepository.findByPercentageGreaterThan(minPercentage);
    }

    public Voucher addVoucher(Voucher voucher) {
        if (voucher.getPercentage() <= 0F) {
            throw new VoucherException("Error when trying to save the Voucher, percentage must be greater than zero");
        }
        return voucherRepository.save(voucher);
    }

    public void deleteVoucher(Voucher voucher) {
        voucherRepository.delete(voucher);
    }

}
