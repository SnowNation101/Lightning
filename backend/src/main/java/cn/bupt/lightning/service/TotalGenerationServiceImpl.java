package cn.bupt.lightning.service;

import cn.bupt.lightning.domain.TotalGeneration;
import cn.bupt.lightning.repo.TotalGenerationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TotalGenerationServiceImpl implements TotalGenerationService {

  private final TotalGenerationRepo repo;

  @Override
  public List<Float> getLastMonthData(int station) {
    // 30 days ago
    LocalDate first;
    Calendar calendar1 = Calendar.getInstance();
    calendar1.add(Calendar.DATE, -30);
    first = calendar1.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    log.info(first.toString());
    // Today
    LocalDate last;
    Calendar calendar2 = Calendar.getInstance();
    last = calendar2.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    log.info(last.toString());
    return repo.findAllByDateBetweenAndStationOrderByDate(first, last, station).stream()
        .map(TotalGeneration::getGeneration)
        .collect(Collectors.toList());
  }

  @Override
  public List<Float> getLastYearData(int station) {
    List<Float> ret = new ArrayList<>();
    for (int i = 12; i >= 1; i--) {
      // First day of month
      LocalDate first;
      Calendar calendar1 = Calendar.getInstance();
      calendar1.add(Calendar.MONTH, -i);
      calendar1.set(Calendar.DAY_OF_MONTH, 1);
      first = calendar1.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      log.info(first.toString());
      // Last day of month
      LocalDate last;
      Calendar calendar2 = Calendar.getInstance();
      calendar2.add(Calendar.MONTH, 1 - i);
      calendar2.set(Calendar.DAY_OF_MONTH, 0);
      last = calendar2.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      log.info(last.toString());
      List<Float> tmp = new ArrayList<>(repo.findAllByDateBetweenAndStationOrderByDate(first, last, station).stream()
          .map(TotalGeneration::getGeneration).toList());
      log.info(tmp.toString());
      float sum = 0;
      for (var t : tmp) {
        sum += t;
      }
      ret.add(sum);
    }
    return ret;
  }
}
