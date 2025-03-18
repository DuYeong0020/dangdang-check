package com.dangdang.check.domain.pet;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

public class PetCommand {

    @Getter
    @ToString
    public static class RegisterPetRequest {
        private final String employeeLoginId;

        private final Long customerId;
        private final String name;
        private final LocalDate birthday;
        private final Boolean neutered;
        private final Boolean vaccinated;
        private final String specialNotes;
        private final Double weight;
        private final String breedName;
        private final Species species;

        @Builder
        public RegisterPetRequest(String employeeLoginId, Long customerId, String name, LocalDate birthday, Boolean neutered, Boolean vaccinated, String specialNotes, Double weight, String breedName, Species species) {
            this.employeeLoginId = employeeLoginId;
            this.customerId = customerId;
            this.name = name;
            this.birthday = birthday;
            this.neutered = neutered;
            this.vaccinated = vaccinated;
            this.specialNotes = specialNotes;
            this.weight = weight;
            this.breedName = breedName;
            this.species = species;
        }

        public Pet toEntity(Breed breed) {
            return Pet.builder()
                    .name(name)
                    .birthday(birthday)
                    .neutered(neutered)
                    .vaccinated(vaccinated)
                    .specialNotes(specialNotes)
                    .weight(weight)
                    .breed(breed)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class ModifyPetRequest {
        private final String employeeLoginId;
        private final Long customerId;
        private final Long petId;
        private final String name;
        private final Gender gender;
        private final LocalDate birthday;
        private final Boolean neutered;
        private final Boolean vaccinated;
        private final String specialNotes;
        private final Double weight;
        private final String breedName;
        private final Species species;

        @Builder
        public ModifyPetRequest(String employeeLoginId, Long customerId, Long petId, String name, Gender gender, LocalDate birthday, Boolean neutered, Boolean vaccinated, String specialNotes, Double weight, String breedName, Species species) {
            this.employeeLoginId = employeeLoginId;
            this.customerId = customerId;
            this.petId = petId;
            this.name = name;
            this.gender = gender;
            this.birthday = birthday;
            this.neutered = neutered;
            this.vaccinated = vaccinated;
            this.specialNotes = specialNotes;
            this.weight = weight;
            this.breedName = breedName;
            this.species = species;
        }
    }
}
