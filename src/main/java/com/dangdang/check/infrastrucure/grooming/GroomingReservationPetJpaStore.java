package com.dangdang.check.infrastrucure.grooming;

import com.dangdang.check.domain.grooming.GroomingReservationPet;
import com.dangdang.check.domain.grooming.GroomingReservationPetStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroomingReservationPetJpaStore implements GroomingReservationPetStore {

    private final GroomingReservationPetJpaRepository groomingReservationPetJpaRepository;

    @Override
    public GroomingReservationPet storeGroomingReservationPet(GroomingReservationPet groomingReservationPet) {
        return groomingReservationPetJpaRepository.save(groomingReservationPet);
    }
}
