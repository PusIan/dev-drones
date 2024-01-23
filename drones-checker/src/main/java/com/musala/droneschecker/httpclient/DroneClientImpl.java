package com.musala.droneschecker.httpclient;

import com.musala.dronesdto.dto.DroneResponseShortDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DroneClientImpl implements DroneClient {
    private final RestTemplate restTemplate;

    private final String apiUrl;

    @Autowired
    public DroneClientImpl(@Value("${services.drone.url}") String apiUrl, RestTemplateBuilder builder) {
        this.apiUrl = apiUrl;
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(apiUrl))
                .build();
    }

    @Override
    public List<DroneResponseShortDto> getDrones() {
        ResponseEntity<List<DroneResponseShortDto>> response = restTemplate.exchange(apiUrl + "/drone",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }
}
