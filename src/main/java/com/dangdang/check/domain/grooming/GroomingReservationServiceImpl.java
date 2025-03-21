package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.customer.Customer;
import com.dangdang.check.domain.customer.CustomerReader;
import com.dangdang.check.domain.pet.Pet;
import com.dangdang.check.domain.pet.PetReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroomingReservationServiceImpl implements GroomingReservationService {

    private final GroomingReservationStore groomingReservationStore;
    private final GroomingReservationValidator groomingReservationValidator;
    private final GroomingReservationPetStore groomingReservationPetStore;
    private final CustomerReader customerReader;
    private final PetReader petReader;

    @Override
    @Transactional
    public GroomingReservationInfo registerGroomingReservation(GroomingReservationCommand.RegisterGroomingReservationRequest request) {
        groomingReservationValidator.checkRegisterGroomingReservation(request);
        Customer customer = customerReader.findById(request.getCustomerId());
        GroomingReservation initGroomingReservation = new GroomingReservation(request.getTitle(), request.getGroomingRequest(), request.getStartAt(), request.getEndAt(), ReservationStatus.RESERVED, customer);
        GroomingReservation groomingReservation = groomingReservationStore.storeGroomingReservation(initGroomingReservation);
        List<Pet> pets = petReader.findAllById(request.getPetIds());
        pets.forEach(pet -> {
            GroomingReservationPet initGroomingReservationPet = new GroomingReservationPet(groomingReservation, pet);
            groomingReservationPetStore.storeGroomingReservationPet(initGroomingReservationPet);
            groomingReservation.addGroomingReservationPet(initGroomingReservationPet);

        });
        return new GroomingReservationInfo(groomingReservation);
    }
}
