package com.dangdang.check.infrastrucure.employee;

import com.dangdang.check.domain.employee.Employee;
import com.dangdang.check.domain.employee.EmployeeStore;
import com.dangdang.check.domain.employee.QEmployee;
import com.dangdang.check.domain.employee.Role;
import com.dangdang.check.domain.store.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeJpaStore implements EmployeeStore {

    private final EmployeeJpaRepository employeeJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Employee storeEmployee(Employee employee) {
        return employeeJpaRepository.save(employee);
    }

    @Override
    public void resetEmployeesOfStore(Long storeId) {
        QEmployee employee = QEmployee.employee;
        jpaQueryFactory.update(employee)
                .set(employee.store, (Store) null)
                .set(employee.role, Role.DEFAULT)
                .where(employee.store.id.eq(storeId)
                        .and(employee.isDeleted.eq(false)))
                .execute();
    }
}