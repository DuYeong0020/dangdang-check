package com.dangdang.check.interfaces.pet;

import com.dangdang.check.common.argumentresolver.Login;
import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.pet.PetCommand;
import com.dangdang.check.domain.pet.PetInfo;
import com.dangdang.check.domain.pet.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
