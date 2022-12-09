package cn.bupt.lightning.service;

import cn.bupt.lightning.domain.WeatherData;
import cn.bupt.lightning.repo.WeatherDataRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherDataServiceImpl implements WeatherDataService {

  private final WeatherDataRepo repo;

  @Override
  public List<WeatherData> get96Data() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, 2022);
    cal.set(Calendar.MONTH, 5);
    cal.set(Calendar.DAY_OF_MONTH, 18);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    LocalDateTime from = cal.getTime().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    log.info(from.toString());

    cal.add(Calendar.DATE, 1);
    LocalDateTime to = cal.getTime().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    return repo.findAllByTimeBetweenOrderByTime(from, to);
  }
}
