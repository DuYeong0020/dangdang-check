package com.dangdang.check.infrastrucure.pet;

import com.dangdang.check.domain.pet.Breed;
import com.dangdang.check.domain.pet.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BreedJpaRepository extends JpaRepository<Breed, Long> {

    Optional<Breed> findByNameAndSpeciesAndIsDeletedFalse(String name, Species species);
}
