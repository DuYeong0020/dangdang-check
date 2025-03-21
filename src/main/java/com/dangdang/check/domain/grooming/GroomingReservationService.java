package com.dangdang.check.domain.grooming;

public interface GroomingReservationService {
    GroomingReservationInfo registerGroomingReservation(GroomingReservationCommand.RegisterGroomingReservationRequest command);
}
