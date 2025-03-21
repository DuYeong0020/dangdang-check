package com.dangdang.check.infrastrucure.grooming;

import com.dangdang.check.domain.grooming.GroomingReservationPet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroomingReservationPetJpaRepository extends JpaRepository<GroomingReservationPet, Long> {
}
