package com.dangdang.check.domain.pet;

import java.util.List;

public interface PetReader {

    Pet findById(Long id);

    List<Pet> findAllById(List<Long> ids);
}
