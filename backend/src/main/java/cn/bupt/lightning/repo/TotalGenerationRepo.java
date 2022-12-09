package cn.bupt.lightning.repo;

import cn.bupt.lightning.domain.TotalGeneration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TotalGenerationRepo extends JpaRepository<TotalGeneration, Long> {
  List<TotalGeneration> findAllByDateBetweenAndStationOrderByDate(LocalDate from, LocalDate to, Integer station);
}
