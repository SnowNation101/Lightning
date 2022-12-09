package cn.bupt.lightning.controller;

import cn.bupt.lightning.domain.PresentData;
import cn.bupt.lightning.repo.PresentDataRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping(value = "/api/test/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PresentDataController {

  private final PresentDataRepo repo;

  @GetMapping("/96-future")
  List<PresentData> get96Future() {
    return repo.findAllByIdBetween(97, 193);
  }

  @GetMapping("/96-error")
  List<Float> get96Error() {
    List<PresentData> list = repo.findAllByIdBetween(1, 97);
    List<Float> ret = new ArrayList<>();
    for (var i : list) {
      ret.add(i.getPredictVal() - i.getRealVal());
    }
    return ret;
  }

  @GetMapping("/last-year")
  List<Float> getLastYear() {
    Random random = new Random();
    List<Float> list = new ArrayList<>();
    for (int i = 0; i < 12; i++) {
      float a = random.nextFloat(700, 900);
      list.add(a);
    }
    return list;
  }

  @GetMapping("/last-month")
  List<Float> getLastMonth() {
    Random random = new Random();
    List<Float> list = new ArrayList<>();
    for (int i = 0; i < 30; i++) {
      float a = random.nextFloat(20, 30);
      list.add(a);
    }
    return list;
  }

  @GetMapping("/is-error")
  int isError() {
    return 0;
  }
}
