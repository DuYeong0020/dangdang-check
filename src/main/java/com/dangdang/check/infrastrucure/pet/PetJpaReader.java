package com.dangdang.check.infrastrucure.pet;

import com.dangdang.check.domain.pet.Pet;
import com.dangdang.check.domain.pet.PetReader;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PetJpaReader implements PetReader {

    private final PetJpaRepository petJpaRepository;

    @Override
    public Pet findById(Long id) {
        return petJpaRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Pet> findAllById(List<Long> ids) {
        return petJpaRepository.findAllByIdInAndIsDeletedFalse(ids);
    }
}