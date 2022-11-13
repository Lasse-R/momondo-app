package com.example.momodoapp.dto;

import com.example.momodoapp.entity.Country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NameResponse {
    
    private String name;

    private String gender;

    private double genderProbability;

    private int age;

    private int ageCount;

    private String country;

    private double countryProbability;

    public NameResponse(GenderResponse genderResponse, AgeResponse ageResponse, CountryResponse countryResponse) {
        this.name = genderResponse.getName();
        this.gender = genderResponse.getGender();
        this.genderProbability = genderResponse.getProbability();

        this.age = ageResponse.getAge();
        this.ageCount = ageResponse.getCount();

        Country country = countryResponse.getMostLikely();
        this.country = country.getCountry_id();
        this.countryProbability = country.getProbability();
    }

}
