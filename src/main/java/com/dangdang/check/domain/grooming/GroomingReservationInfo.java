package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.customer.Customer;
import com.dangdang.check.domain.pet.Pet;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
public class GroomingReservationInfo {
    private final Long id;
    private final String title;
    private final String groomingRequest;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final ReservationStatus reservationStatus;
    private final CustomerInfo customerInfo;
    private final List<PetInfo> petInfos;

    public GroomingReservationInfo(GroomingReservation groomingReservation) {
        this.id = groomingReservation.getId();
        this.title = groomingReservation.getTitle();
        this.groomingRequest = groomingReservation.getGroomingRequest();
        this.startAt = groomingReservation.getStartAt();
        this.endAt = groomingReservation.getEndAt();
        this.reservationStatus = groomingReservation.getReservationStatus();
        this.customerInfo = new CustomerInfo(groomingReservation.getCustomer());
        this.petInfos = groomingReservation.getGroomingReservationPets().stream()
                .map(groomingReservationPet -> new PetInfo(groomingReservationPet.getPet()))
                .toList();
    }

    @Getter
    @ToString
    public static class CustomerInfo {
        private final Long id;
        private final String name;


        public CustomerInfo(Customer customer) {
            this.id = customer.getId();
            this.name = customer.getName();
        }
    }

    @Getter
    @ToString
    public static class PetInfo {
        private final Long id;
        private final String name;

        public PetInfo(Pet pet) {
            this.id = pet.getId();
            this.name = pet.getName();
        }
    }


}
