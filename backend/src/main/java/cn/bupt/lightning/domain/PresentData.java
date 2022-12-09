package cn.bupt.lightning.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PresentData {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Float humidity;
  private Float lightIntensity;
  private Float temperature;
  private Float windSpeed;
  private Float predictVal;
  private Float realVal;
}
