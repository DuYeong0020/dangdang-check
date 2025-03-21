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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDto {

    @Getter
    @ToString
    public static class RegisterCustomerWithPetsRequest {

        private String customerName;

        @NotBlank(message = "전화번호는 필수 입력 값입니다.")
        @Pattern(
                regexp = "^(0\\d{1,2}\\d{3,4}\\d{4}|01[016789]\\d{7,8})$",
                message = "올바른 전화번호 형식이 아닙니다. (예: 0212345678 또는 01012345678)"
        )
        private String phoneNumber;

        private String phoneLabel;

        @NotNull(message = "전화번호 유형은 필수입니다.")
        private PhoneType phoneType;

        private String specialNotes;

        private List<@Valid PetRequest> petsRequest;

        public CustomerCommand.RegisterCustomerWithPetsRequest toCommand(String loginId) {
            return CustomerCommand.RegisterCustomerWithPetsRequest.builder()
                    .loginId(loginId)
                    .customerName(customerName)
                    .phoneNumber(phoneNumber)
                    .phoneLabel(phoneLabel)
                    .phoneType(phoneType)
                    .specialNotes(specialNotes)
                    .pets(petsRequest != null ?
                            petsRequest.stream()
                            .map(PetRequest::toCommand)
                            .collect(Collectors.toList())
                            : Collections.emptyList())
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
            this.customerPhonesResponse = customerInfo.getPhones() != null
                    ? customerInfo.getPhones().stream()
                    .map(CustomerPhoneResponse::new)
                    .collect(Collectors.toList())
                    : Collections.emptyList();
            this.petsResponse = customerInfo.getPets() != null
                    ? customerInfo.getPets().stream()
                    .map(PetResponse::new)
                    .collect(Collectors.toList())
                    : Collections.emptyList();
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
    @Getter
    @ToString
    public static class ModifyCustomerRequest {

        @NotBlank(message = "보호자 이름은 필수입니다.")
        private String name;

        private String specialNotes;

        public CustomerCommand.ModifyCustomerRequest toCommand(String loginId, Long customerId) {
            return CustomerCommand.ModifyCustomerRequest.builder()
                    .loginId(loginId)
                    .customerId(customerId)
                    .name(name)
                    .specialNotes(specialNotes)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class ModifyCustomerResponse {
        private final Long id;
        private final String name;
        private final String specialNotes;

        public ModifyCustomerResponse(CustomerInfo customerInfo) {
            this.id = customerInfo.getId();
            this.name = customerInfo.getName();
            this.specialNotes = customerInfo.getSpecialNotes();
        }
    }
}
