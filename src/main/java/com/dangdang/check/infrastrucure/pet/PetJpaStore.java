package com.dangdang.check.infrastrucure.pet;

import com.dangdang.check.domain.pet.Pet;
import com.dangdang.check.domain.pet.PetStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PetJpaStore implements PetStore {

    private final PetJpaRepository petJpaRepository;

    @Override
    public Pet storePet(Pet pet) {
        return petJpaRepository.save(pet);
    }
}
