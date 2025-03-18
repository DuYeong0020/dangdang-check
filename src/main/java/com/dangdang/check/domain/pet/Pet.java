package com.dangdang.check.domain.pet;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 이름
    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별
    private Boolean neutered; // 중성화 여부
    private Boolean vaccinated; // 예방접종 여부
    @Lob
    private String specialNotes; // 특이사항
    private LocalDate birthday; // 생년월일
    private Double weight; // 체중

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Builder
    public Pet(String name, Gender gender, Boolean neutered, Boolean vaccinated, String specialNotes, LocalDate birthday, Double weight, Breed breed) {
        if (!StringUtils.hasText(name)) throw new InvalidParameterException();
        if (vaccinated == null) throw new InvalidParameterException();
        if (breed == null) throw new InvalidParameterException();

        this.name = name;
        this.gender = gender;
        this.neutered = neutered;
        this.vaccinated = vaccinated;
        this.specialNotes = specialNotes;
        this.birthday = birthday;
        this.weight = weight;
        this.breed = breed;
    }


    public void modifyName(String name) {
        if (StringUtils.hasText(name)) {
            this.name = name;
        }
    }

    public void modifyGender(Gender gender) {
        this.gender = gender;
    }

    public void modifyNeutered(Boolean neutered) {
        this.neutered = neutered;
    }

    public void modifyVaccinated(Boolean vaccinated) {
        if (vaccinated != null) {
            this.vaccinated = vaccinated;
        }
    }

    public void modifySpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    public void modifyBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void modifyWeight(Double weight) {
        if (weight != null && weight > 0) {
            this.weight = weight;
        }
    }

    public void modifyBreed(Breed breed) {
        if (breed != null) {
            this.breed = breed;
        }
    }

    public void modifyCustomer(Customer customer) {
        this.customer = customer;
    }
}
