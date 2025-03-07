package com.dangdang.check.interfaces.store;

import com.dangdang.check.domain.store.StoreCommand;
import com.dangdang.check.domain.store.StoreInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

public class StoreDto {

    @Getter
    @ToString
    public static class RegisterStoreRequest {
        @NotBlank(message = "매장 이름은 필수입니다.")
        private String storeName;

        @Valid
        @NotNull(message = "매장 주소는 필수입니다.")
        private AddressRequest storeAddress;

        @NotBlank(message = "매장 이메일은 필수입니다.")
        @Email(message = "유효한 이메일 주소를 입력하세요.")
        private String storeEmail;

        @NotBlank(message = "대표 전화번호는 필수입니다.")
        @Pattern(regexp = "^(\\d{2,3}-\\d{3,4}-\\d{4})$", message = "유효한 전화번호 형식이어야 합니다. (예: 02-1234-5678)")
        private String mainPhone;

        @NotBlank(message = "사업자 등록번호는 필수입니다.")
        @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$", message = "유효한 사업자 등록번호 형식이어야 합니다. (예: 123-45-67890)")
        private String businessRegistrationNumber;

        @NotBlank(message = "사업자명은 필수입니다.")
        private String businessName;

        @NotBlank(message = "대표자명은 필수입니다.")
        private String representativeName;

        @NotBlank(message = "업종은 필수입니다.")
        private String businessType;

        @Valid
        @NotNull(message = "사업장 주소는 필수입니다.")
        private AddressRequest businessAddress;

        public StoreCommand.RegisterStoreRequest toCommand(String loginId) {
            return StoreCommand.RegisterStoreRequest.builder()
                    .loginId(loginId)
                    .storeName(storeName)
                    .storeEmail(storeEmail)
                    .mainPhone(mainPhone)
                    .storeAddress(storeAddress.toCommand())
                    .businessRegistrationNumber(businessRegistrationNumber)
                    .businessName(businessName)
                    .representativeName(representativeName)
                    .businessType(businessType)
                    .businessAddress(businessAddress.toCommand())
                    .build();
        }
    }

    @Getter
    @ToString
    public static class AddressRequest {

        @NotBlank(message = "우편번호는 필수입니다.")
        private String zipCode;

        @NotBlank(message = "시/도를 입력해야 합니다.")
        private String state;

        @NotBlank(message = "시/군/구를 입력해야 합니다.")
        private String city;

        @NotBlank(message = "도로명 주소를 입력해야 합니다.")
        private String street;

        private String detail;

        public StoreCommand.AddressRequest toCommand() {
            return StoreCommand.AddressRequest.builder()
                    .zipCode(zipCode)
                    .state(state)
                    .city(city)
                    .street(street)
                    .detail(detail)
                    .build();
        }
    }


    @Getter
    @ToString
    public static class RegisterStoreResponse {

        private final Long ownerId;
        private final String storeName;
        private final AddressResponse storeAddress;
        private final String storeEmail;
        private final String mainPhone;

        private final String businessRegistrationNumber;
        private final String businessName;
        private final String representativeName;
        private final String businessType;
        private final AddressResponse businessAddress;
        private final String registrationStatus;

        public RegisterStoreResponse(StoreInfo storeInfo) {
            this.ownerId = storeInfo.getOwnerId();
            this.storeName = storeInfo.getStoreName();
            this.storeAddress = new AddressResponse(storeInfo.getStoreAddress());
            this.storeEmail = storeInfo.getStoreEmail();
            this.mainPhone = storeInfo.getMainPhone();
            this.businessRegistrationNumber = storeInfo.getBusinessRegistrationNumber();
            this.businessName = storeInfo.getBusinessName();
            this.representativeName = storeInfo.getRepresentativeName();
            this.businessType = storeInfo.getBusinessType();
            this.businessAddress = new AddressResponse(storeInfo.getBusinessAddress());
            this.registrationStatus = storeInfo.getRegistrationStatus();
        }
    }

    @Getter
    @ToString
    public static class AddressResponse {

        private final String zipCode;
        private final String state;
        private final String city;
        private final String street;
        private final String detail;

        public AddressResponse(StoreInfo.AddressInfo addressInfo) {
            this.zipCode = addressInfo.getZipCode();
            this.state = addressInfo.getState();
            this.city = addressInfo.getCity();
            this.street = addressInfo.getStreet();
            this.detail = addressInfo.getDetail();
        }
    }
}
