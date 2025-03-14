package com.dangdang.check.infrastrucure.pet;

import com.dangdang.check.domain.pet.Breed;
import com.dangdang.check.domain.pet.BreedStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BreedJpaStore implements BreedStore {

    private final BreedJpaRepository breedJpaRepository;

    @Override
    public Breed storeBreed(Breed breed) {
        return breedJpaRepository.save(breed);
    }
}
