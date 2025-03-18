package com.dangdang.check.infrastrucure.pet;

import com.dangdang.check.common.exception.UnauthorizedException;
import com.dangdang.check.domain.customer.Customer;
import com.dangdang.check.domain.customer.CustomerReader;
import com.dangdang.check.domain.employee.Employee;
import com.dangdang.check.domain.employee.EmployeeReader;
import com.dangdang.check.domain.pet.Pet;
import com.dangdang.check.domain.pet.PetCommand;
import com.dangdang.check.domain.pet.PetReader;
import com.dangdang.check.domain.pet.PetValidator;
import com.dangdang.check.domain.store.RegistrationStatus;
import com.dangdang.check.domain.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PetValidatorImpl implements PetValidator {
    private final EmployeeReader employeeReader;
    private final CustomerReader customerReader;
    private final PetReader petReader;

    @Override
    public void checkRegisterPet(PetCommand.RegisterPetRequest request) {
        Employee employee = employeeReader.findByLoginId(request.getEmployeeLoginId());
        Customer customer = customerReader.findById(request.getCustomerId());
        checkEmployeeHasStore(employee);
        checkStoreIsOpen(employee.getStore());
        checkSameStore(customer, employee);
    }

    @Override
    public void checkModifyPet(PetCommand.ModifyPetRequest request) {
        Employee employee = employeeReader.findByLoginId(request.getEmployeeLoginId());
        Customer customer = customerReader.findById(request.getCustomerId());
        Pet pet = petReader.findById(request.getPetId());
        checkSameCustomer(pet, customer);
        checkEmployeeHasStore(employee);
        checkStoreIsOpen(employee.getStore());
        checkSameStore(customer, employee);
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
            throw new UnauthorizedException("해당 고객의 펫을 등록할 권한이 없습니다.");
        }
    }

    private void checkSameCustomer(Pet pet, Customer customer) {
        if (pet.getCustomer() == null) {
            throw new RuntimeException("펫의 소유자 정보가 없습니다.");
        }

        if (!pet.getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("잘못된 소유자 정보입니다.");
        }
    }
}
