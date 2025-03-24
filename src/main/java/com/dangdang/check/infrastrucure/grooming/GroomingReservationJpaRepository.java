package com.dangdang.check.infrastrucure.grooming;

import com.dangdang.check.domain.grooming.GroomingReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroomingReservationJpaRepository extends JpaRepository<GroomingReservation, Long> {

    Optional<GroomingReservation> findByIdAndIsDeletedFalse(Long id);
}