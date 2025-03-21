package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.pet.Pet;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.InvalidParameterException;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroomingReservationPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grooming_reservation_id", nullable = false)
    private GroomingReservation groomingReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    public GroomingReservationPet(GroomingReservation groomingReservation, Pet pet) {
        if (groomingReservation == null) throw new InvalidParameterException();
        if (pet == null) throw new InvalidParameterException();

        this.groomingReservation = groomingReservation;
        this.pet = pet;
    }

    public void addGroomingReservation(GroomingReservation groomingReservation) {
        if (groomingReservation != null) {
            this.groomingReservation = groomingReservation;
        }
    }
}
