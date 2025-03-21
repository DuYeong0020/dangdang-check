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
        private final String title;
        private final String groomingRequest;
        private final LocalDateTime startAt;
        private final LocalDateTime endAt;
        private final Long customerId;
        private final List<Long> petIds;

        @Builder
        public RegisterGroomingReservationRequest(String title, String groomingRequest, LocalDateTime startAt, LocalDateTime endAt, Long customerId, List<Long> petIds) {
            this.title = title;
            this.groomingRequest = groomingRequest;
            this.startAt = startAt;
            this.endAt = endAt;
            this.customerId = customerId;
            this.petIds = petIds;
        }
    }
}
