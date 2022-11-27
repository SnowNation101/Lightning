package cn.bupt.lightning.controller;

import cn.bupt.lightning.service.GenerationPowerService;
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
@RequestMapping(value = "/api/power")
@RequiredArgsConstructor
public class GenerationPowerController {

  @Autowired
  private final GenerationPowerService service;

  @RequestMapping(
      value = "/96-predict",
      method = RequestMethod.POST
  )
  List<Float> get96PredictData(@RequestParam int station) {
    return service.get96predict(station);
  }

  @RequestMapping(
      value = "/96-error",
      method = RequestMethod.POST
  )
  List<Float> get96Error(@RequestParam int station) {
    return service.get96error(station);
  }

  @RequestMapping(
      value = "/run",
      method = RequestMethod.POST
  )
  Void runPythonScript(@RequestParam int station) {
    return null;
  }
}
