package cn.bupt.lightning.repo;

import cn.bupt.lightning.domain.PresentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresentDataRepo extends JpaRepository<PresentData, Long> {
  List<PresentData> findAllByIdBetween(long from, long to);
}
