package com.dangdang.check.infrastrucure.pet;

import com.dangdang.check.domain.pet.Breed;
import com.dangdang.check.domain.pet.BreedReader;
import com.dangdang.check.domain.pet.Species;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BreedJpaReader implements BreedReader {

    private final BreedJpaRepository breedJpaRepository;


    @Override
    public Optional<Breed> findByNameAndSpecies(String name, Species species) {
        return breedJpaRepository.findByNameAndSpeciesAndIsDeletedFalse(name, species);
    }
}
