package com.example.momodoapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.momodoapp.dto.AgeResponse;
import com.example.momodoapp.dto.CountryResponse;
import com.example.momodoapp.dto.GenderResponse;
import com.example.momodoapp.dto.NameResponse;

import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class NameController {
    
    @GetMapping("/name")
    public Mono<NameResponse> getName(@RequestParam("name")String name) {
        WebClient webClient = WebClient.create();

        Mono<GenderResponse> monoGenderResponse = webClient.get()
        .uri("https://api.genderize.io?name=" + name)
        .retrieve()
        .bodyToMono(GenderResponse.class);

        Mono<AgeResponse> monoAgeResponse = webClient.get()
        .uri("https://api.agify.io?name=" + name)
        .retrieve()
        .bodyToMono(AgeResponse.class);

        Mono<CountryResponse> monoCountryResponse = webClient.get()
        .uri("https://api.nationalize.io?name=" + name)
        .retrieve()
        .bodyToMono(CountryResponse.class);

        return Mono.zip(
                monoGenderResponse,
                monoAgeResponse,
                monoCountryResponse
        ).map(t -> new NameResponse(t.getT1(), t.getT2(), t.getT3()));

    }
}
