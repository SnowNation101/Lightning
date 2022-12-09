package cn.bupt.lightning.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class WeatherData {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Float id;
  private LocalDateTime time;
  private Float temperature;
  private Float humidity;
  private Float lightIntensity;
  private Float windSpeed;
}
