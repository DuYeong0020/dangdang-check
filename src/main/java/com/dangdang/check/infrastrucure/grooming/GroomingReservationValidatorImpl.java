package com.dangdang.check.infrastrucure.grooming;

import com.dangdang.check.common.exception.UnauthorizedException;
import com.dangdang.check.domain.customer.Customer;
import com.dangdang.check.domain.customer.CustomerReader;
import com.dangdang.check.domain.employee.Employee;
import com.dangdang.check.domain.employee.EmployeeReader;
import com.dangdang.check.domain.grooming.GroomingReservationCommand;
import com.dangdang.check.domain.grooming.GroomingReservationValidator;
import com.dangdang.check.domain.pet.Pet;
import com.dangdang.check.domain.pet.PetReader;
import com.dangdang.check.domain.store.RegistrationStatus;
import com.dangdang.check.domain.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GroomingReservationValidatorImpl implements GroomingReservationValidator {

    private final PetReader petReader;
    private final CustomerReader customerReader;
    private final EmployeeReader employeeReader;

    @Override
    public void checkRegisterGroomingReservation(GroomingReservationCommand.RegisterGroomingReservationRequest request) {
        Employee employee = employeeReader.findByLoginId(request.getEmployeeLoginId());
        Customer customer = customerReader.findById(request.getCustomerId());
        checkEmployeeHasStore(employee);
        checkStoreIsOpen(employee.getStore());
        checkSameStore(customer, employee);
        checkPetsBelongToCustomer(request.getPetIds(), request.getCustomerId());
        checkReservationTimeValid(request.getStartAt(), request.getEndAt());
    }

    private void checkStoreIsOpen(Store store) {
        if (store.isDeleted() || store.getBusinessInfo().isDeleted()
                || store.getBusinessInfo().getRegistrationStatus() == RegistrationStatus.REJECTED
                || store.getBusinessInfo().getRegistrationStatus() == RegistrationStatus.PENDING) {
            throw new RuntimeException("가게가 삭제되었습니다.");
        }
    }

    private void checkEmployeeHasStore(Employee employee) {
        if (employee.getStore() == null) {
            throw new RuntimeException("직원이 속한 가게(Store)가 없습니다.");
        }
    }

    private void checkSameStore(Customer customer, Employee employee) {
        if (customer.getStore() == null || employee.getStore() == null) {
            throw new UnauthorizedException("고객 또는 직원의 가게 정보가 올바르지 않습니다.");
        }

        if (!customer.getStore().getId().equals(employee.getStore().getId())) {
            throw new UnauthorizedException("해당 고객의 펫을 미용 예약할 권한이 없습니다.");
        }
    }

    private void checkPetsBelongToCustomer(List<Long> petIds, Long customerId) {
        List<Pet> pets = petReader.findAllById(petIds);

        boolean allOwned = pets.stream()
                .allMatch(pet -> pet.getCustomer().getId().equals(customerId));

        if (!allOwned) {
            throw new IllegalArgumentException("고객이 소유하지 않은 반려동물이 포함되어 있습니다.");
        }
    }

    private void checkReservationTimeValid(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt) || startAt.isEqual(endAt)) {
            throw new IllegalArgumentException("예약 종료 시간은 시작 시간보다 늦어야 합니다.");
        }
    }
}