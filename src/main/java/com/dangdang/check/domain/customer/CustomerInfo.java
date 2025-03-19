package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.pet.Pet;
import com.dangdang.check.domain.pet.Species;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@ToString
public class CustomerInfo {
    private final Long id;
    private final String name;
    private final String specialNotes;
    private final List<CustomerPhoneInfo> phones;
    private final List<PetInfo> pets;

    public CustomerInfo(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.specialNotes = customer.getSpecialNotes();
        this.phones = customer.getPhones() != null
                ? customer.getPhones().stream()
                .map(CustomerPhoneInfo::new)
                .collect(Collectors.toList())
                : Collections.emptyList();
        this.pets = customer.getPets() != null
                ? customer.getPets().stream()
                .map(PetInfo::new)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }

    @Getter
    @ToString
    public static class PetInfo {
        private final Long id;
        private final String name;
        private final Boolean vaccinated;
        private final String breedName;
        private final Species species;

        public PetInfo(Pet pet) {
            this.id = pet.getId();
            this.name = pet.getName();
            this.vaccinated = pet.getVaccinated();
            this.breedName = pet.getBreed().getName();
            this.species = pet.getBreed().getSpecies();
        }
    }

    @Getter
    @ToString
    public static class CustomerPhoneInfo {
        private final Long id;
        private final String label;
        private final String phoneNumber;
        private final PhoneType phoneType;

        public CustomerPhoneInfo(CustomerPhone customerPhone) {
            this.id = customerPhone.getId();
            this.label = customerPhone.getLabel();
            this.phoneNumber = customerPhone.getPhoneNumber();
            this.phoneType = customerPhone.getPhoneType();
        }
    }
}
