package cn.bupt.lightning.controller;

import cn.bupt.lightning.service.TotalGenerationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/generation")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TotalGenerationController {

  @Autowired
  private final TotalGenerationService service;

  @RequestMapping(
      value = "/last-year",
      method = RequestMethod.POST
  )
  List<Float> getLastYearData(@RequestParam int station) {
    return service.getLastYearData(station);
  }

  @RequestMapping(
      value = "/last-month",
      method = RequestMethod.POST
  )
  List<Float> getLastMonthData(@RequestParam int station) {
    return service.getLastMonthData(station);
  }

}
