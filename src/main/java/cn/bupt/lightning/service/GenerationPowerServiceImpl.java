package cn.bupt.lightning.service;

import cn.bupt.lightning.domain.GenerationPower;
import cn.bupt.lightning.domain.TotalGeneration;
import cn.bupt.lightning.repo.GenerationPowerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenerationPowerServiceImpl implements GenerationPowerService{

  private final GenerationPowerRepo repo;

  @Override
  public List<Float> get96predict(int station) {
    LocalDateTime from = LocalDateTime.now();
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, 1);
    LocalDateTime to = cal.getTime().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    return repo.findAllByTimeBetweenAndStationOrderByTime(from, to, station).stream()
        .map(GenerationPower::getPredict)
        .collect(Collectors.toList());
  }
  @Override
  public List<Float> get96error(int station) {
    LocalDateTime from;
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -1);
    from = cal.getTime().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    LocalDateTime to = LocalDateTime.now();
    List<Float> predict = repo.findAllByTimeBetweenAndStationOrderByTime(from, to, station).stream()
        .map(GenerationPower::getPredict).toList();
    List<Float> real = repo.findAllByTimeBetweenAndStationOrderByTime(from, to, station).stream()
        .map(GenerationPower::getReal).toList();
    if(predict.size() == real.size()) {
      List<Float> ret = new ArrayList<>();
      for (int i = 0; i < predict.size(); i++) {
        ret.add(predict.get(i) - real.get(i));
      }
      return ret;
    }
    return null;
  }
}
