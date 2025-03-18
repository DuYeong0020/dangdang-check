package com.dangdang.check.domain.pet;

import com.dangdang.check.domain.customer.Customer;
import com.dangdang.check.domain.customer.CustomerReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetValidator petValidator;
    private final PetStore petStore;
    private final PetReader petReader;
    private final BreedStore breedStore;
    private final BreedReader breedReader;
    private final CustomerReader customerReader;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public PetInfo modifyPet(PetCommand.ModifyPetRequest request) {
        petValidator.checkModifyPet(request);
        Pet pet = petReader.findById(request.getPetId());
        pet.modifyName(request.getName());
        pet.modifyGender(request.getGender());
        pet.modifyBirthday(request.getBirthday());
        pet.modifyNeutered(request.getNeutered());
        pet.modifyVaccinated(request.getVaccinated());
        pet.modifySpecialNotes(request.getSpecialNotes());
        pet.modifyWeight(request.getWeight());
        Breed breed = breedReader.findByNameAndSpecies(request.getBreedName(), request.getSpecies())
                .orElseGet(() -> {
                    Breed initBreed = new Breed(request.getBreedName(), request.getSpecies());
                    return breedStore.storeBreed(initBreed);
                });
        pet.modifyBreed(breed);
        return new PetInfo(pet);
    }
}
