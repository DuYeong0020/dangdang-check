package com.dangdang.check.domain.grooming;

public interface GroomingReservationService {
    GroomingReservationInfo registerGroomingReservation(GroomingReservationCommand.RegisterGroomingReservationRequest command);

    GroomingReservationInfo modifyGroomingReservation(GroomingReservationCommand.ModifyGroomingReservationRequest command);
}
