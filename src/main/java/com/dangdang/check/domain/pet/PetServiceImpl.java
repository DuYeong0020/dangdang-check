package com.dangdang.check.domain.pet;

import com.dangdang.check.domain.customer.Customer;
import com.dangdang.check.domain.customer.CustomerReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetValidator petValidator;
    private final PetStore petStore;
    private final BreedStore breedStore;
    private final BreedReader breedReader;
    private final CustomerReader customerReader;

    @Override
    public PetInfo registerPet(PetCommand.RegisterPetRequest request) {
        petValidator.checkRegisterPet(request);
        Breed breed = breedReader.findByNameAndSpecies(request.getBreedName(), request.getSpecies())
                .orElseGet(() -> {
                    Breed initBreed = new Breed(request.getBreedName(), request.getSpecies());
                    return breedStore.storeBreed(initBreed);
                });
        Customer customer = customerReader.findById(request.getCustomerId());

        Pet initPet = request.toEntity(breed);
        customer.addPet(initPet);
        Pet pet = petStore.storePet(initPet);
        return new PetInfo(pet);
    }
}
