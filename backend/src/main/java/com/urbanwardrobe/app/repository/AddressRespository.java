package com.urbanwardrobe.app.repository;

import com.urbanwardrobe.app.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRespository extends JpaRepository<Address, Long> {
}
