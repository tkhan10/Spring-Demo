package com.example.springdemo.controller;

import com.example.springdemo.model.Country;
import com.example.springdemo.service.CountryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
public class CountryResource {

    private final CountryClient countryClient;

    public CountryResource(CountryClient countryClient){
        this.countryClient = countryClient;
    }

    @GetMapping("")
    public List<String> getAllEuropeanFrenchSpeakingCountries(){
        long start = System.currentTimeMillis();
        List<Country> countriesByLanguage = countryClient.getCountriesByLanguage("fr");
        List<Country> countriesByRegion = countryClient.getCountriesByRegion("eu");

        List<String> europeanFrenchSpeakingCountries = new ArrayList<>(countriesByLanguage.stream().map(Country::getName).collect(Collectors.toList()));
            europeanFrenchSpeakingCountries.retainAll(countriesByRegion.stream().map(Country::getName).collect(Collectors.toList()));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
            return europeanFrenchSpeakingCountries;
    }

    @GetMapping("/stream")
    public List<String> getAllEuropeanFrenchSpeakingCountriesAsync() throws Throwable{
        long start = System.currentTimeMillis();
        CompletableFuture<List<Country>> countriesByLanguageAsync = countryClient.getCountriesByLanguageAsync("fr");
        CompletableFuture<List<Country>> countriesByRegionAsync = countryClient.getCountriesByRegionAsync("eu");
        List<String> europeanFrenchSpeakingCountries;
        try {
            europeanFrenchSpeakingCountries = new ArrayList<>(countriesByLanguageAsync.get().stream().map(Country::getName).collect(Collectors.toList()));
            europeanFrenchSpeakingCountries.retainAll(countriesByRegionAsync.get().stream().map(Country::getName).collect(Collectors.toList()));

        }catch (Throwable b){
            throw b.getCause();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        return europeanFrenchSpeakingCountries;
    }

}
