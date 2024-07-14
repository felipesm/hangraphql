package com.ifdeveloper.service;

import com.ifdeveloper.exception.VoucherException;
import com.ifdeveloper.exception.VoucherNotFoundException;
import com.ifdeveloper.model.Voucher;
import com.ifdeveloper.repository.VoucherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;


    @Cacheable(value = "voucher", key = "#id")
    public Voucher getVoucher(String id) {
        var voucher = voucherRepository.findById(id).orElse(null);
        if (Objects.isNull(voucher)) {
            throw new VoucherNotFoundException(String.format("Voucher with id %s not found", id));
        }
        log.info("Voucher founded in database");
        return voucher;
    }

    public List<Voucher> getVouchers(Pageable pageable) {
        return voucherRepository.findAll(pageable).getContent();
    }

    public List<Voucher> getVouchersByPercentage(Float minPercentage) {
        return voucherRepository.findByPercentageGreaterThan(minPercentage);
    }

    public Voucher addVoucher(Voucher voucher) {
        if (validPercentage(voucher.getPercentage())) {
            throw new VoucherException("Error when trying to save the Voucher, percentage must be greater than zero");
        }
        return voucherRepository.save(voucher);
    }

    @CachePut(value = "voucher", key = "#voucher.id")
    public Voucher updateVoucher(Voucher voucher) {
        if (validPercentage(voucher.getPercentage())) {
            throw new VoucherException("Error when trying to save the Voucher, percentage must be greater than zero");
        }

        return voucherRepository.save(voucher);
    }

    @CacheEvict(value = "voucher", key = "#voucher.id")
    public void deleteVoucher(Voucher voucher) {
        voucherRepository.delete(voucher);
    }

    private boolean validPercentage(Float percentage) {
        return percentage <= 0F;
    }

}
