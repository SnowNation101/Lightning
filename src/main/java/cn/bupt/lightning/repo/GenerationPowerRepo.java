package cn.bupt.lightning.repo;

import cn.bupt.lightning.domain.GenerationPower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GenerationPowerRepo extends JpaRepository<GenerationPower, Long> {
  List<GenerationPower> findAllByTimeBetweenAndStationOrderByTime(LocalDateTime from,
                                                                  LocalDateTime to,
                                                                  Integer station);
}
