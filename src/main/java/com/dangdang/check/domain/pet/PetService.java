package com.dangdang.check.domain.pet;

public interface PetService {

    PetInfo registerPet(PetCommand.RegisterPetRequest request);
}
