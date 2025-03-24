package com.dangdang.check.domain.grooming;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

public class GroomingReservationCommand {

    @Getter
    @ToString
    public static class RegisterGroomingReservationRequest {

        private final String employeeLoginId;
        private final String title;
        private final String groomingRequest;
        private final LocalDateTime startAt;
        private final LocalDateTime endAt;
        private final Long customerId;
        private final List<Long> petIds;

        @Builder
        public RegisterGroomingReservationRequest(String employeeLoginId, String title, String groomingRequest, LocalDateTime startAt, LocalDateTime endAt, Long customerId, List<Long> petIds) {
            this.employeeLoginId = employeeLoginId;
            this.title = title;
            this.groomingRequest = groomingRequest;
            this.startAt = startAt;
            this.endAt = endAt;
            this.customerId = customerId;
            this.petIds = petIds;
        }
    }

    @Getter
    @ToString
    public static class ModifyGroomingReservationRequest {

        private final String employeeLoginId;
        private final Long groomingReservationId;
        private final String title;
        private final String groomingRequest;
        private final LocalDateTime startAt;
        private final LocalDateTime endAt;
        private final ReservationStatus reservationStatus;
        private final Long customerId;
        private final List<Long> petIds;

        @Builder
        public ModifyGroomingReservationRequest(String employeeLoginId, Long groomingReservationId, String title, String groomingRequest, LocalDateTime startAt, LocalDateTime endAt, ReservationStatus reservationStatus, Long customerId, List<Long> petIds) {
            this.employeeLoginId = employeeLoginId;
            this.groomingReservationId = groomingReservationId;
            this.title = title;
            this.groomingRequest = groomingRequest;
            this.startAt = startAt;
            this.endAt = endAt;
            this.reservationStatus = reservationStatus;
            this.customerId = customerId;
            this.petIds = petIds;
        }
    }
}
