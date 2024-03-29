package cn.bupt.lightning.service;

import cn.bupt.lightning.domain.GenerationPower;
import cn.bupt.lightning.domain.PresentData;
import cn.bupt.lightning.domain.WeatherData;
import cn.bupt.lightning.repo.GenerationPowerRepo;
import cn.bupt.lightning.repo.WeatherDataRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenerationPowerServiceImpl implements GenerationPowerService {

  private final GenerationPowerRepo generationPowerRepo;
  private final WeatherDataRepo weatherDataRepo;

//  @Override
//  public List<Float> get96predict(int station) {
//    LocalDateTime from = LocalDateTime.now();
//    Calendar cal = Calendar.getInstance();
//    cal.add(Calendar.DATE, 1);
//    LocalDateTime to = cal.getTime().toInstant()
//        .atZone(ZoneId.systemDefault())
//        .toLocalDateTime();
//    return repo.findAllByTimeBetweenAndStationOrderByTime(from, to, station).stream()
//        .map(GenerationPower::getPredictVal)
//        .collect(Collectors.toList());
//  }

  @Override
  public List<PresentData> get96predict(int station) {
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

    cal.add(Calendar.DATE, 1);
    LocalDateTime to = cal.getTime().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    Calendar now = Calendar.getInstance();
    cal.add(Calendar.DATE, -1);
    cal.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
    cal.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
    cal.set(Calendar.SECOND, now.get(Calendar.SECOND));
    cal.set(Calendar.MILLISECOND, now.get(Calendar.MILLISECOND));
    LocalDateTime now_time = cal.getTime().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    List<Float> predictList_1 = generationPowerRepo.findAllByTimeBetweenAndStationOrderByTime(now_time, to, station)
        .stream()
        .map(GenerationPower::getPredictVal)
        .toList();

    List<WeatherData> weatherList_1 = weatherDataRepo.findAllByTimeBetweenOrderByTime(now_time, to);

    List<Float> predictList_2 = generationPowerRepo.findAllByTimeBetweenAndStationOrderByTime(from, now_time, station)
        .stream()
        .map(GenerationPower::getPredictVal)
        .toList();
    List<WeatherData> weatherList_2 = weatherDataRepo.findAllByTimeBetweenOrderByTime(from, now_time);

    List<PresentData> ret = new ArrayList<>();

    for (int i = 0; i < weatherList_1.size(); i++) {
      PresentData presentData = new PresentData();
      presentData.setPredictVal(predictList_1.get(i));
      presentData.setHumidity(weatherList_1.get(i).getHumidity());
      presentData.setTemperature(weatherList_1.get(i).getTemperature());
      presentData.setLightIntensity(weatherList_1.get(i).getLightIntensity());
      presentData.setWindSpeed(weatherList_1.get(i).getWindSpeed());
      ret.add(presentData);
    }

    for (int i = 0; i < weatherList_2.size(); i++) {
      PresentData presentData = new PresentData();
      presentData.setPredictVal(predictList_2.get(i));
      presentData.setHumidity(weatherList_2.get(i).getHumidity());
      presentData.setTemperature(weatherList_2.get(i).getTemperature());
      presentData.setLightIntensity(weatherList_2.get(i).getLightIntensity());
      presentData.setWindSpeed(weatherList_2.get(i).getWindSpeed());
      ret.add(presentData);
    }

    return ret;
  }

//  @Override
//  public List<Float> get96error(int station) {
//    LocalDateTime from;
//    Calendar cal = Calendar.getInstance();
//    cal.add(Calendar.DATE, -1);
//    from = cal.getTime().toInstant()
//        .atZone(ZoneId.systemDefault())
//        .toLocalDateTime();
//    LocalDateTime to = LocalDateTime.now();
//    List<Float> predict = repo.findAllByTimeBetweenAndStationOrderByTime(from, to, station).stream()
//        .map(GenerationPower::getPredictVal).toList();
//    List<Float> real = repo.findAllByTimeBetweenAndStationOrderByTime(from, to, station).stream()
//        .map(GenerationPower::getRealVal).toList();
//    if (predict.size() == real.size()) {
//      List<Float> ret = new ArrayList<>();
//      for (int i = 0; i < predict.size(); i++) {
//        ret.add(predict.get(i) - real.get(i));
//      }
//      return ret;
//    }
//    return null;
//  }

  @Override
  public List<Float> get96error(int station) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, 2022);
    cal.set(Calendar.MONTH, 5);
    cal.set(Calendar.DAY_OF_MONTH, 17);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    LocalDateTime from = cal.getTime().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    log.info(from.toString());

    cal.add(Calendar.DATE, 1);
    cal.add(Calendar.MILLISECOND, -1);
    LocalDateTime to = cal.getTime().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    log.info(to.toString());

    List<Float> predict = generationPowerRepo.findAllByTimeBetweenAndStationOrderByTime(from, to, station).stream()
        .map(GenerationPower::getPredictVal).toList();
    log.info(predict.toString());

    List<Float> real = generationPowerRepo.findAllByTimeBetweenAndStationOrderByTime(from, to, station).stream()
        .map(GenerationPower::getRealVal).toList();
    log.info(real.toString());

    log.info(String.valueOf(predict.size()));
    log.info(String.valueOf(real.size()));

    if (predict.size() == real.size()) {
      List<Float> ret = new ArrayList<>();
      for (int i = 0; i < predict.size(); i++) {
        ret.add(predict.get(i) - real.get(i));
      }
      return ret;
    }
    return null;
  }
}
