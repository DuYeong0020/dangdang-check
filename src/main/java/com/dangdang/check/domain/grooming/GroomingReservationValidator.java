package com.dangdang.check.domain.grooming;

public interface GroomingReservationValidator {
    void checkRegisterGroomingReservation(GroomingReservationCommand.RegisterGroomingReservationRequest request);
}
