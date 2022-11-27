package cn.bupt.lightning.service;

import java.util.List;

public interface GenerationPowerService {
  List<Float> get96predict(int station);
  List<Float> get96error(int station);
}
