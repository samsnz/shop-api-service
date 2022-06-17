package com.shop.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.service.models.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}
