package com.dangdang.check.domain.grooming;

public interface GroomingReservationValidator {
    void checkRegisterGroomingReservation(GroomingReservationCommand.RegisterGroomingReservationRequest request);

    void checkModifyGroomingReservation(GroomingReservationCommand.ModifyGroomingReservationRequest request);
}
