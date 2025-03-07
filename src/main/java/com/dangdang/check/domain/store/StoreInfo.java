package com.dangdang.check.domain.store;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StoreInfo {
    private final Long ownerId;
    private final String storeName;
    private final AddressInfo storeAddress;
    private final String storeEmail;
    private final String mainPhone;

    private final String businessRegistrationNumber;
    private final String businessName;
    private final String representativeName;
    private final String businessType;
    private final AddressInfo businessAddress;
    private final String registrationStatus;

    public StoreInfo(Store store) {
        this.ownerId = store.getOwner().getId();
        this.storeName = store.getName();
        this.storeAddress = new AddressInfo(store.getAddress());
        this.storeEmail = store.getEmail();
        this.mainPhone = store.getMainPhone();
        this.businessRegistrationNumber = store.getBusinessInfo().getBusinessRegistrationNumber();
        this.businessName = store.getBusinessInfo().getBusinessName();
        this.representativeName = store.getBusinessInfo().getRepresentativeName();
        this.businessType = store.getBusinessInfo().getBusinessType();
        this.businessAddress = new AddressInfo(store.getBusinessInfo().getBusinessAddress());
        this.registrationStatus = store.getBusinessInfo().getRegistrationStatus().name();
    }

    @Getter
    @ToString
    public static class AddressInfo {
        private final String zipCode;
        private final String state;
        private final String city;
        private final String street;
        private final String detail;

        public AddressInfo(Address address) {
            this.zipCode = address.getZipCode();
            this.state = address.getState();
            this.city = address.getCity();
            this.street = address.getStreet();
            this.detail = address.getDetail();
        }

    }
}
