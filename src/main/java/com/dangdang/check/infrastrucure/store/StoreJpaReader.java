package com.dangdang.check.infrastrucure.store;

import com.dangdang.check.domain.store.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreJpaReader implements StoreReader {

    private final StoreJpaRepository storeJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Store findById(Long id) {
        return storeJpaRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<StoreSummaryInfo> findByCriteria(StoreCriteria.GetStores criteria) {
        QStore store = QStore.store;
        QBusinessInfo businessInfo = QBusinessInfo.businessInfo;

        BooleanBuilder predicate = new BooleanBuilder();

        if (criteria.getRegistrationStatus() != null) {
            predicate.and(businessInfo.registrationStatus.eq(criteria.getRegistrationStatus()));
        }

        List<StoreSummaryInfo> content = jpaQueryFactory.select(new QStoreSummaryInfo(
                        store.id,
                        store.name,
                        businessInfo.representativeName,
                        businessInfo.registrationStatus,
                        store.mainPhone)
                ).from(store)
                .join(store.businessInfo, businessInfo)
                .where(predicate)
                .offset(criteria.getPageable().getOffset())
                .limit(criteria.getPageable().getPageSize())
                .fetch();

        return new PageImpl<>(content, criteria.getPageable(), content.size());
    }
}