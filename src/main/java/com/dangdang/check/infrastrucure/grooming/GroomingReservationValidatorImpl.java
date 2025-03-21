package com.dangdang.check.infrastrucure.grooming;

import com.dangdang.check.domain.grooming.GroomingReservationCommand;
import com.dangdang.check.domain.grooming.GroomingReservationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroomingReservationValidatorImpl implements GroomingReservationValidator {

    @Override
    public void checkRegisterGroomingReservation(GroomingReservationCommand.RegisterGroomingReservationRequest request) {
    }
}