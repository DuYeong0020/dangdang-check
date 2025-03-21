package com.dangdang.check.infrastrucure.pet;

import com.dangdang.check.domain.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetJpaRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findByIdAndIsDeletedFalse(Long id);

    List<Pet> findAllByIdInAndIsDeletedFalse(List<Long> ids);
}
