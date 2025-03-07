package com.dangdang.check.domain.store;

import com.dangdang.check.domain.employee.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class StoreCommand {

    @Getter
    @ToString
    public static class RegisterStoreRequest {
        private final String loginId;
        private final String storeName;
        private final AddressRequest storeAddress;
        private final String storeEmail;
        private final String mainPhone;

        private final String businessRegistrationNumber;
        private final String businessName;
        private final String representativeName;
        private final String businessType;
        private final AddressRequest businessAddress;

        @Builder
        public RegisterStoreRequest(String loginId, String storeName, AddressRequest storeAddress, String storeEmail, String mainPhone, String businessRegistrationNumber, String businessName, String representativeName, String businessType, AddressRequest businessAddress) {
            this.loginId = loginId;
            this.storeName = storeName;
            this.storeAddress = storeAddress;
            this.storeEmail = storeEmail;
            this.mainPhone = mainPhone;
            this.businessRegistrationNumber = businessRegistrationNumber;
            this.businessName = businessName;
            this.representativeName = representativeName;
            this.businessType = businessType;
            this.businessAddress = businessAddress;
        }

        public Store toEntity(RegistrationStatus registrationStatus, Employee owner) {
            return Store.builder()
                    .name(storeName)
                    .address(storeAddress.toEntity())
                    .email(storeEmail)
                    .mainPhone(mainPhone)
                    .owner(owner)
                    .businessInfo(BusinessInfo.builder()
                            .businessRegistrationNumber(businessRegistrationNumber)
                            .businessName(businessName)
                            .representativeName(representativeName)
                            .businessType(businessType)
                            .businessAddress(businessAddress.toEntity())
                            .registrationStatus(registrationStatus)
                            .build())
                    .build();
        }
    }
    @Getter
    @ToString
    public static class AddressRequest {
        private final String zipCode;
        private final String state;
        private final String city;
        private final String street;
        private final String detail;

        @Builder
        public AddressRequest(String zipCode, String state, String city, String street, String detail) {
            this.zipCode = zipCode;
            this.state = state;
            this.city = city;
            this.street = street;
            this.detail = detail;
        }

        public Address toEntity() {
            return Address.builder()
                    .zipCode(zipCode)
                    .state(state)
                    .city(city)
                    .street(street)
                    .detail(detail)
                    .build();
        }
    }
}
