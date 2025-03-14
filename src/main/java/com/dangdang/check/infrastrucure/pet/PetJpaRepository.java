package com.dangdang.check.infrastrucure.pet;

import com.dangdang.check.domain.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetJpaRepository extends JpaRepository<Pet, Long> {
}
