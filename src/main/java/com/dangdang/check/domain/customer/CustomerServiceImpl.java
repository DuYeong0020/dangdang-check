package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.employee.Employee;
import com.dangdang.check.domain.employee.EmployeeReader;
import com.dangdang.check.domain.pet.*;
import com.dangdang.check.domain.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerValidator customerValidator;
    private final EmployeeReader employeeReader;
    private final CustomerStore customerStore;
    private final PetStore petStore;
    private final BreedStore breedStore;
    private final BreedReader breedReader;


    @Override
    @Transactional
    public CustomerInfo registerCustomerWithPets(CustomerCommand.RegisterCustomerWithPetsRequest request) {
        customerValidator.checkRegisterCustomerWithPets(request);

        Employee employee = employeeReader.findByLoginId(request.getLoginId());
        Store store = employee.getStore();
        CustomerPhone initCustomerPhone = new CustomerPhone(request.getPhoneNumber(), request.getPhoneLabelOrDefault(), request.getPhoneType());
        Customer initCustomer = request.toEntity(store);
        initCustomer.addPhone(initCustomerPhone);
        Customer customer = customerStore.storeCustomer(initCustomer);

        request.getPets().forEach(petRequest -> {
            Breed breed = breedReader.findByNameAndSpecies(petRequest.getBreedName(), petRequest.getSpecies())
                    .orElseGet(() -> {
                        Breed initBreed = new Breed(petRequest.getBreedName(), petRequest.getSpecies());
                        return breedStore.storeBreed(initBreed);
                    });
            Pet initPet = petRequest.toEntity(breed);
            Pet pet = petStore.storePet(initPet);
            customer.addPet(pet);
        });

        return new CustomerInfo(customer);
    }
}
