package com.sunshineadvisory.finappapi.repositories;

import com.sunshineadvisory.finappapi.models.Don;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonRepository extends JpaRepository<Don, Long> {
}