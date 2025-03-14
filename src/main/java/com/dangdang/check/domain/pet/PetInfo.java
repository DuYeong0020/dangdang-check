package com.dangdang.check.domain.pet;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PetInfo {
    private final Long id;
    private final String name;
    private final String breedName;
    private final Species species;
    private final String specialNotes;
    private final Double weight;
    private final Boolean neutered;
    private final Boolean vaccinated;

    public PetInfo(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.breedName = pet.getBreed().getName();
        this.species = pet.getBreed().getSpecies();
        this.specialNotes = pet.getSpecialNotes();
        this.weight = pet.getWeight();
        this.neutered = pet.getNeutered();
        this.vaccinated = pet.getVaccinated();
    }

}
