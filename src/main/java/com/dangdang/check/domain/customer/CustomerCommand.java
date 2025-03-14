package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.pet.Breed;
import com.dangdang.check.domain.pet.Pet;
import com.dangdang.check.domain.pet.Species;
import com.dangdang.check.domain.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

public class CustomerCommand {

    @Getter
    @ToString
    public static class RegisterCustomerWithPetsRequest {
        private final String loginId;

        private final String customerName;
        private final String phoneNumber;
        private final String phoneLabel;
        private final PhoneType phoneType;
        private final String specialNotes;
        private final List<PetRequest> pets;

        @Builder
        public RegisterCustomerWithPetsRequest(String loginId, String customerName, String phoneNumber, String phoneLabel, PhoneType phoneType, String specialNotes, List<PetRequest> pets) {
            this.loginId = loginId;
            this.customerName = customerName;
            this.phoneNumber = phoneNumber;
            this.phoneLabel = phoneLabel;
            this.phoneType = phoneType;
            this.specialNotes = specialNotes;
            this.pets = pets;
        }

        public Customer toEntity(Store store) {
            return Customer.builder()
                    .name(getCustomerNameOrDefault())
                    .specialNotes(specialNotes)
                    .store(store)
                    .build();
        }

        public String getCustomerNameOrDefault() {
            return (customerName != null && !customerName.isBlank())
                    ? customerName : pets.get(0).getName() + " 보호자";
        }

        public String getPhoneLabelOrDefault() {
            return (phoneLabel != null && !phoneLabel.isBlank()) ? phoneLabel : "보호자";
        }

    }

    @Getter
    public static class PetRequest {
        private final String name;
        private final LocalDate birthday;
        private final Boolean neutered;
        private final Boolean vaccinated;
        private final String specialNotes;
        private final Double weight;
        private final String breedName;
        private final Species species;

        @Builder
        public PetRequest(String name, LocalDate birthday, Boolean neutered, Boolean vaccinated, String specialNotes, Double weight, String breedName, Species species) {
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

}
