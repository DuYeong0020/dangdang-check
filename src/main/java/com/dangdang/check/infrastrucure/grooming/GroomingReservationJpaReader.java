package com.dangdang.check.infrastrucure.grooming;

import com.dangdang.check.domain.grooming.GroomingReservation;
import com.dangdang.check.domain.grooming.GroomingReservationReader;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroomingReservationJpaReader implements GroomingReservationReader {

    private final GroomingReservationJpaRepository groomingReservationJpaRepository;

    @Override
    public GroomingReservation findById(Long id) {
        return groomingReservationJpaRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
