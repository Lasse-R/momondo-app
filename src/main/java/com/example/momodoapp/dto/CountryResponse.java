package com.example.momodoapp.dto;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.momodoapp.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountryResponse {

    private List<Country> country;

    private String name;

    public Country getMostLikely() {
        Optional<Country> countryOptional = country.stream()
                .max(Comparator.comparingDouble(Country::getProbability));

        return countryOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }
    
}
