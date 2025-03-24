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
    private final GroomingReservationReader groomingReservationReader;
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

    @Override
    @Transactional
    public GroomingReservationInfo modifyGroomingReservation(GroomingReservationCommand.ModifyGroomingReservationRequest request) {
        groomingReservationValidator.checkModifyGroomingReservation(request);
        GroomingReservation groomingReservation = groomingReservationReader.findById(request.getGroomingReservationId());
        updateCustomer(groomingReservation, request.getCustomerId());
        updatePets(groomingReservation, request.getPetIds());
        updateReservationDetails(groomingReservation, request);
        return new GroomingReservationInfo(groomingReservation);
    }

    private void updateReservationDetails(GroomingReservation groomingReservation, GroomingReservationCommand.ModifyGroomingReservationRequest request) {
        groomingReservation.modifyTitle(request.getTitle());
        groomingReservation.modifyGroomingRequest(request.getGroomingRequest());
        groomingReservation.modifyStartAt(request.getStartAt());
        groomingReservation.modifyEndAt(request.getEndAt());
        groomingReservation.modifyReservationStatus(request.getReservationStatus());
    }

    private void updatePets(GroomingReservation groomingReservation, List<Long> petIds) {
        if (petIds != null) {
            //추가해야할 pet
            List<Pet> newPets = petReader.findAllById(petIds);

            // 현재 예약중인 pet
            List<GroomingReservationPet> currentPets = groomingReservation.getGroomingReservationPets();

            // 삭제해야 할 pet
            List<GroomingReservationPet> toRemove = currentPets
                    .stream()
                    .filter(groomingReservationPet -> !newPets.contains(groomingReservationPet.getPet()))
                    .toList();

            // 추가해야할 pet 찾기
            List<Pet> toAdd = newPets.stream()
                    .filter(pet -> currentPets.stream()
                            .noneMatch(groomingReservationPet -> groomingReservationPet.getPet().equals(pet)))
                    .toList();

            toRemove.forEach(groomingReservationPetStore::hardDelete);
            groomingReservation.getGroomingReservationPets().removeAll(toRemove);

            toAdd.forEach(pet -> {
                GroomingReservationPet newGroomingReservationPet = new GroomingReservationPet(groomingReservation, pet);
                groomingReservationPetStore.storeGroomingReservationPet(newGroomingReservationPet);
                groomingReservation.addGroomingReservationPet(newGroomingReservationPet);
            });
        }
    }

    private void updateCustomer(GroomingReservation groomingReservation, Long customerId) {
        if (customerId != null) {
            Customer customer = customerReader.findById(customerId);
            groomingReservation.modifyCustomer(customer);
        }
    }
}
