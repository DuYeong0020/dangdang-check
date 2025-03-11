package com.dangdang.check.infrastrucure.store;

import com.dangdang.check.domain.store.RegistrationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

public class StoreCriteria {

    @Getter
    @ToString
    public static class GetStores {

        private final Pageable pageable;
        private final RegistrationStatus registrationStatus;

        @Builder
        public GetStores(Pageable pageable, RegistrationStatus registrationStatus) {
            this.pageable = pageable;
            this.registrationStatus = registrationStatus;
        }


    }
}
