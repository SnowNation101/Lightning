package cn.bupt.lightning.repo;

import cn.bupt.lightning.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherDataRepo extends JpaRepository<WeatherData, Long> {
  List<WeatherData> findAllByTimeBetweenOrderByTime(LocalDateTime from, LocalDateTime to);
}
