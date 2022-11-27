package cn.bupt.lightning.service;

import cn.bupt.lightning.domain.WeatherData;

import java.util.List;

public interface WeatherDataService {
  List<WeatherData> get96Data();
}
