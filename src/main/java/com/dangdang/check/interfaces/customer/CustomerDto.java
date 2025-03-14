package com.dangdang.check.interfaces.customer;

import com.dangdang.check.domain.customer.CustomerCommand;
import com.dangdang.check.domain.customer.CustomerInfo;
import com.dangdang.check.domain.customer.PhoneType;
import com.dangdang.check.domain.pet.Species;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDto {

    @Getter
    @ToString
    public static class RegisterCustomerWithPetsRequest {
        private String customerName;
        private String phoneNumber;
        private String phoneLabel;
        @NotNull(message = "전화번호 유형은 필수입니다.")
        private PhoneType phoneType;
        private String specialNotes;
        @NotEmpty(message = "반려동물 정보는 최소 1개 이상이어야 합니다.")
        private List<@Valid PetRequest> petsRequest;

        public CustomerCommand.RegisterCustomerWithPetsRequest toCommand(String loginId) {
            return CustomerCommand.RegisterCustomerWithPetsRequest.builder()
                    .loginId(loginId)
                    .customerName(customerName)
                    .phoneNumber(phoneNumber)
                    .phoneLabel(phoneLabel)
                    .phoneType(phoneType)
                    .specialNotes(specialNotes)
                    .pets(petsRequest.stream()
                            .map(PetRequest::toCommand)
                            .collect(Collectors.toList()))
                    .build();
        }

        @Getter
        @ToString
        public static class PetRequest {
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

            public CustomerCommand.PetRequest toCommand() {
                return CustomerCommand.PetRequest.builder()
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
    }

    @Getter
    @ToString
    public static class RegisterCustomerWithPetsResponse {
        private final Long id;
        private final String name;
        private final List<CustomerPhoneResponse> customerPhonesResponse;
        private final List<PetResponse> petsResponse;

        public RegisterCustomerWithPetsResponse(CustomerInfo customerInfo) {
            this.id = customerInfo.getId();
            this.name = customerInfo.getName();
            this.customerPhonesResponse = customerInfo.getPhones()
                    .stream()
                    .map(CustomerPhoneResponse::new)
                    .collect(Collectors.toList());
            this.petsResponse = customerInfo.getPets()
                    .stream()
                    .map(PetResponse::new)
                    .collect(Collectors.toList());
        }

        @Getter
        @ToString
        public static class PetResponse {
            private final Long id;
            private final String name;
            private final Boolean vaccinated;
            private final String breedName;
            private final Species species;

            public PetResponse(CustomerInfo.PetInfo petInfo) {
                this.id = petInfo.getId();
                this.name = petInfo.getName();
                this.vaccinated = petInfo.getVaccinated();
                this.breedName = petInfo.getBreedName();
                this.species = petInfo.getSpecies();
            }
        }

        @Getter
        @ToString
        public static class CustomerPhoneResponse {
            private final Long id;
            private final String label;
            private final String phoneNumber;
            private final PhoneType phoneType;

            public CustomerPhoneResponse(CustomerInfo.CustomerPhoneInfo customerPhoneInfo) {
                this.id = customerPhoneInfo.getId();
                this.label = customerPhoneInfo.getLabel();
                this.phoneNumber = customerPhoneInfo.getPhoneNumber();
                this.phoneType = customerPhoneInfo.getPhoneType();
            }
        }
    }
}
