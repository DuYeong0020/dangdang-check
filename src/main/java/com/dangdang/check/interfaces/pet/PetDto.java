package com.dangdang.check.interfaces.pet;

import com.dangdang.check.domain.pet.PetCommand;
import com.dangdang.check.domain.pet.PetInfo;
import com.dangdang.check.domain.pet.Species;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

public class PetDto {

    @Getter
    @ToString
    public static class RegisterPetRequest {

        @NotBlank(message = "반려동물 이름은 필수입니다.")
        private String name;

        @Past(message = "생일은 현재 날짜보다 이전이어야 합니다.")
        private LocalDate birthday;

        private Boolean neutered;

        @NotNull(message = "백신 접종 여부는 필수입니다.")
        private Boolean vaccinated;

        private String specialNotes;

        @Positive(message = "몸무게는 0보다 커야 합니다.")
        private Double weight;

        @NotBlank(message = "견종 이름은 필수입니다.")
        private String breedName;

        @NotNull(message = "반려동물 종(Species)은 필수입니다.")
        private Species species;

        public PetCommand.RegisterPetRequest toCommand(String loginId, Long customerId) {
            return PetCommand.RegisterPetRequest.builder()
                    .employeeLoginId(loginId)
                    .customerId(customerId)
                    .name(name)
                    .birthday(birthday)
                    .neutered(neutered)
                    .vaccinated(vaccinated)
                    .specialNotes(specialNotes)
                    .weight(weight)
                    .breedName(breedName)
                    .species(species)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterPetResponse {
        private final Long id;
        private final String name;
        private final String breedName;
        private final Species species;
        private final String specialNotes;
        private final Double weight;
        private final Boolean neutered;
        private final Boolean vaccinated;

        public RegisterPetResponse(PetInfo petInfo) {
            this.id = petInfo.getId();
            this.name = petInfo.getName();
            this.breedName = petInfo.getBreedName();
            this.species = petInfo.getSpecies();
            this.specialNotes = petInfo.getSpecialNotes();
            this.weight = petInfo.getWeight();
            this.neutered = petInfo.getNeutered();
            this.vaccinated = petInfo.getVaccinated();
        }
    }

}
