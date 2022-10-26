package com.example.springdemo.service;

import com.example.springdemo.model.Country;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CountryClient {

    RestTemplate restTemplate = new RestTemplate();

    public List<Country> getCountriesByLanguage(String language) {
        System.out.println("Inside getCountriesByLanguage");
        String url = "https://restcountries.com/v2/lang/"+ language;
        Country[] response = restTemplate.getForObject(url, Country[].class);
        return Arrays.asList(response);
    }


    public List<Country> getCountriesByRegion(String region) {
        System.out.println("Inside getCountriesByRegion");
        String url = "https://restcountries.com/v2/regionalbloc/"+ region;
        Country[] response = restTemplate.getForObject(url, Country[].class);
        return Arrays.asList(response);
    }

    public CompletableFuture<List<Country>> getCountriesByLanguageAsync(String language) {
        System.out.println("Inside getCountriesByLanguage");
        String url = "https://restcountries.com/v2/lang/"+ language;
        Country[] response = restTemplate.getForObject(url, Country[].class);
        return CompletableFuture.completedFuture(Arrays.asList(response));
    }


    public CompletableFuture<List<Country>> getCountriesByRegionAsync(String region) {
        System.out.println("Inside getCountriesByRegion");
        String url = "https://restcountries.com/v2/regionalbloc/"+ region;
        Country[] response = restTemplate.getForObject(url, Country[].class);
        return CompletableFuture.completedFuture(Arrays.asList(response));
    }
}
