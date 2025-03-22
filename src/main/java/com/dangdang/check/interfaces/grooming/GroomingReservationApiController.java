package com.dangdang.check.interfaces.grooming;

import com.dangdang.check.common.argumentresolver.Login;
import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.grooming.GroomingReservationCommand;
import com.dangdang.check.domain.grooming.GroomingReservationInfo;
import com.dangdang.check.domain.grooming.GroomingReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroomingReservationApiController {

    private final GroomingReservationService groomingReservationService;

    @PostMapping("/api/grooming-reservations")
    public CommonResponse<GroomingReservationDto.RegisterGroomingReservationResponse> registerGroomingReservation(@Login String loginId,
                                                                                                                  @RequestBody GroomingReservationDto.RegisterGroomingReservationRequest request) {
        GroomingReservationCommand.RegisterGroomingReservationRequest command = request.toCommand(loginId);
        GroomingReservationInfo groomingReservationInfo = groomingReservationService.registerGroomingReservation(command);
        GroomingReservationDto.RegisterGroomingReservationResponse response = new GroomingReservationDto.RegisterGroomingReservationResponse(groomingReservationInfo);
        return CommonResponse.success(response);
    }
}
