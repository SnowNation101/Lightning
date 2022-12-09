package cn.bupt.lightning.controller;

import cn.bupt.lightning.domain.PresentData;
import cn.bupt.lightning.service.GenerationPowerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/power")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GenerationPowerController {

  @Autowired
  private final GenerationPowerService service;

  @RequestMapping(
      value = "/96-predict",
      method = RequestMethod.POST
  )
  List<PresentData> get96PredictData(@RequestParam int station) throws IOException {
    runPythonScript();
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
      method = RequestMethod.GET
  )
  Void runPythonScript() throws IOException {
    String line = "D:/ProgramData/Anaconda3/envs/lightning/python.exe  D:\\dev\\lightning\\backend\\src\\main\\py_script\\model_using_withsql.py";
    CommandLine cmdLine = CommandLine.parse(line);

    DefaultExecutor executor = new DefaultExecutor();

    executor.execute(cmdLine);
    return null;
  }

}
