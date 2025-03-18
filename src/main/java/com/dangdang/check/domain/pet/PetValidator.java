package com.dangdang.check.domain.pet;

public interface PetValidator {

    void checkRegisterPet(PetCommand.RegisterPetRequest request);

    void checkModifyPet(PetCommand.ModifyPetRequest request);
}
