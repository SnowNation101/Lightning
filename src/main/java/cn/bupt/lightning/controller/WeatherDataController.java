package cn.bupt.lightning.controller;

import cn.bupt.lightning.domain.WeatherData;
import cn.bupt.lightning.service.WeatherDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/weather")
@RequiredArgsConstructor
public class WeatherDataController {

  @Autowired
  private final WeatherDataService service;

  @RequestMapping(
      value = "get-96",
      method = RequestMethod.POST
  )
  List<WeatherData> get96WeatherData(@RequestParam int station) {
    return service.get96Data();
  }
}
