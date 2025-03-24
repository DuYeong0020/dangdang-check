package com.dangdang.check.interfaces.grooming;

import com.dangdang.check.domain.grooming.GroomingReservationCommand;
import com.dangdang.check.domain.grooming.GroomingReservationInfo;
import com.dangdang.check.domain.grooming.ReservationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

public class GroomingReservationDto {

    @Getter
    @ToString
    public static class RegisterGroomingReservationRequest {

        @NotBlank(message = "제목은 필수 입력 항목입니다.")
        @Size(max = 30, message = "제목은 최대 30자까지 입력 가능합니다.")
        private String title;

        @Size(max = 500, message = "요청사항은 최대 500자까지 입력 가능합니다.")
        private String groomingRequest;

        @NotNull(message = "시작 시간은 필수 입력 항목입니다.")
        private LocalDateTime startAt;

        @NotNull(message = "종료 시간은 필수 입력 항목입니다.")
        private LocalDateTime endAt;

        @NotNull(message = "고객 ID는 필수 입력 항목입니다.")
        private Long customerId;

        @NotEmpty(message = "최소한 한 마리 이상의 반려견을 선택해야 합니다.")
        private List<Long> petIds;

        public GroomingReservationCommand.RegisterGroomingReservationRequest toCommand(String loginId) {
            return GroomingReservationCommand.RegisterGroomingReservationRequest.builder()
                    .employeeLoginId(loginId)
                    .title(title)
                    .groomingRequest(groomingRequest)
                    .startAt(startAt)
                    .endAt(endAt)
                    .customerId(customerId)
                    .petIds(petIds)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterGroomingReservationResponse {

        private final Long id;
        private final String title;
        private final String groomingRequest;
        private final LocalDateTime startAt;
        private final LocalDateTime endAt;
        private final String reservationStatus;
        private final CustomerResponse customerResponse;
        private final List<PetResponse> petResponses;

        public RegisterGroomingReservationResponse(GroomingReservationInfo groomingReservationInfo) {
            this.id = groomingReservationInfo.getId();
            this.title = groomingReservationInfo.getTitle();
            this.groomingRequest = groomingReservationInfo.getGroomingRequest();
            this.startAt = groomingReservationInfo.getStartAt();
            this.endAt = groomingReservationInfo.getEndAt();
            this.reservationStatus = groomingReservationInfo.getReservationStatus().name();
            this.customerResponse = new CustomerResponse(groomingReservationInfo.getCustomerInfo());
            this.petResponses = groomingReservationInfo.getPetInfos().stream()
                    .map(PetResponse::new)
                    .toList();
        }
    }

    @Getter
    @ToString
    public static class ModifyGroomingReservationRequest {

        private String title;
        private String groomingRequest;
        private LocalDateTime startAt;
        private LocalDateTime endAt;
        private ReservationStatus reservationStatus;
        private Long customerId;
        private List<Long> petIds;

        public GroomingReservationCommand.ModifyGroomingReservationRequest toCommand(String loginId, Long reservationId) {
            return GroomingReservationCommand.ModifyGroomingReservationRequest.builder()
                    .employeeLoginId(loginId)
                    .groomingReservationId(reservationId)
                    .title(title)
                    .groomingRequest(groomingRequest)
                    .startAt(startAt)
                    .endAt(endAt)
                    .reservationStatus(reservationStatus)
                    .customerId(customerId)
                    .customerId(customerId)
                    .petIds(petIds)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class ModifyGroomingReservationResponse {

        private final Long id;
        private final String title;
        private final String groomingRequest;
        private final LocalDateTime startAt;
        private final LocalDateTime endAt;
        private final String reservationStatus;
        private final CustomerResponse customerResponse;
        private final List<PetResponse> petResponses;

        public ModifyGroomingReservationResponse(GroomingReservationInfo groomingReservationInfo) {
            this.id = groomingReservationInfo.getId();
            this.title = groomingReservationInfo.getTitle();
            this.groomingRequest = groomingReservationInfo.getGroomingRequest();
            this.startAt = groomingReservationInfo.getStartAt();
            this.endAt = groomingReservationInfo.getEndAt();
            this.reservationStatus = groomingReservationInfo.getReservationStatus().name();
            this.customerResponse = new CustomerResponse(groomingReservationInfo.getCustomerInfo());
            this.petResponses = groomingReservationInfo.getPetInfos().stream()
                    .map(PetResponse::new)
                    .toList();
        }
    }

    @Getter
    @ToString
    public static class CustomerResponse {

        private final Long id;
        private final String name;

        public CustomerResponse(GroomingReservationInfo.CustomerInfo customerInfo) {
            this.id = customerInfo.getId();
            this.name = customerInfo.getName();
        }
    }

    @Getter
    @ToString
    public static class PetResponse {

        private final Long id;
        private final String name;

        public PetResponse(GroomingReservationInfo.PetInfo petInfo) {
            this.id = petInfo.getId();
            this.name = petInfo.getName();
        }
    }
}
