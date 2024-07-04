package com.ifdeveloper.repository;

import com.ifdeveloper.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, String> {

    List<Voucher> findByPercentageGreaterThan(Float minPercentage);
}
