package com.dangdang.check.infrastrucure.grooming;

import com.dangdang.check.domain.grooming.GroomingReservation;
import com.dangdang.check.domain.grooming.GroomingReservationStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroomingReservationJpaStore implements GroomingReservationStore {

    private final GroomingReservationJpaRepository groomingReservationJpaRepository;

    @Override
    public GroomingReservation storeGroomingReservation(GroomingReservation groomingReservation) {
        return groomingReservationJpaRepository.save(groomingReservation);
    }
}
