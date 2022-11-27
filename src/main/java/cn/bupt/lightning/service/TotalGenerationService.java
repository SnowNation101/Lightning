package cn.bupt.lightning.service;

import cn.bupt.lightning.domain.TotalGeneration;

import java.util.List;

public interface TotalGenerationService {
  List<Float> getLastMonthData(int station);
  List<Float> getLastYearData(int station);
}
