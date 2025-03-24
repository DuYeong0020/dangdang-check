package com.dangdang.check.domain.grooming;

public interface GroomingReservationPetStore {

    GroomingReservationPet storeGroomingReservationPet(GroomingReservationPet groomingReservationPet);

    void hardDelete(GroomingReservationPet groomingReservationPet);
}
