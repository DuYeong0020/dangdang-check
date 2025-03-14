package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.pet.Pet;
import com.dangdang.check.domain.store.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name; // 이름
    @Lob
    private String specialNotes; // 특이사항

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "customer")
    private List<Pet> pets = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<CustomerPhone> phones = new ArrayList<>();

}
