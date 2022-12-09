package cn.bupt.lightning.controller;

import cn.bupt.lightning.domain.WeatherData;
import cn.bupt.lightning.service.WeatherDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/weather")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WeatherDataController {

  @Autowired
  private final WeatherDataService service;

  @RequestMapping(
      value = "get-96",
      method = RequestMethod.GET
  )
  List<WeatherData> get96WeatherData() {
    return service.get96Data();
  }
}
