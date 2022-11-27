package cn.bupt.lightning.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TotalGeneration {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Integer station;
  private LocalDate date;
  private Float generation;

  public TotalGeneration(Integer station, LocalDate date, Float generation) {
    this.station = station;
    this.date = date;
    this.generation = generation;
  }
}
