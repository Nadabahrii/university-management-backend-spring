package com.example.pidev1.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;

    private LocalDateTime date;

    private Double temperature;

    private String description;

    public WeatherResponse(String cityName, LocalDateTime date, Double temperature, String description) {
        this.cityName = cityName;
        this.date = date;
        this.temperature = temperature;
        this.description = description;
    }
}

