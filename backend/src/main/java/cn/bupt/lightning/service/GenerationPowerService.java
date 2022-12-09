package cn.bupt.lightning.service;

import cn.bupt.lightning.domain.PresentData;

import java.util.List;

public interface GenerationPowerService {
  List<PresentData> get96predict(int station);

  List<Float> get96error(int station);
}
