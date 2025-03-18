package com.dangdang.check.interfaces.pet;

import com.dangdang.check.common.argumentresolver.Login;
import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.pet.PetCommand;
import com.dangdang.check.domain.pet.PetInfo;
import com.dangdang.check.domain.pet.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PetApiController {

    private final PetService petService;

    @PostMapping("/api/customers/{customerId}/pets")
    public CommonResponse<PetDto.RegisterPetResponse> registerPet(@Login String loginId, @PathVariable Long customerId,
                                                                  @RequestBody PetDto.RegisterPetRequest request) {
        PetCommand.RegisterPetRequest command = request.toCommand(loginId, customerId);
        PetInfo petInfo = petService.registerPet(command);
        PetDto.RegisterPetResponse response = new PetDto.RegisterPetResponse(petInfo);
        return CommonResponse.success(response);
    }

    @PatchMapping("/api/customers/{customerId}/pets/{petId}")
    public CommonResponse<PetDto.ModifyPetResponse> modifyPet(@Login String loginId, @PathVariable Long customerId, @PathVariable Long petId,
                                                              @RequestBody PetDto.ModifyPetRequest request) {
        PetCommand.ModifyPetRequest command = request.toCommand(loginId, customerId, petId);
        PetInfo petInfo = petService.modifyPet(command);
        PetDto.ModifyPetResponse response = new PetDto.ModifyPetResponse(petInfo);
        return CommonResponse.success(response);
    }

}
