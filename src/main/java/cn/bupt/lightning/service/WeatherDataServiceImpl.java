package cn.bupt.lightning.service;

import cn.bupt.lightning.domain.WeatherData;
import cn.bupt.lightning.repo.WeatherDataRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherDataServiceImpl implements WeatherDataService{

  private final WeatherDataRepo repo;

  @Override
  public List<WeatherData> get96Data() {
    LocalDateTime from;
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -1);
    from = cal.getTime().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    LocalDateTime to = LocalDateTime.now();
    return repo.findAllByTimeBetweenOrderByTime(from, to);
  }
}
