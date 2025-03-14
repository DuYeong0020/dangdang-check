package com.dangdang.check.domain.pet;

import java.util.Optional;

public interface BreedReader {

    Optional<Breed> findByNameAndSpecies(String name, Species species);
}
