package cn.bupt.lightning.controller;

import cn.bupt.lightning.domain.PresentData;
import cn.bupt.lightning.service.GenerationPowerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    try {
      ProcessBuilder pb = new ProcessBuilder("D:/ProgramData/Anaconda3/envs/lightning/python.exe",
          "D:/dev/lightning/backend/src/main/py_script/model_using_with_sql.py");
      pb.redirectErrorStream(true);
      Process process = pb.start();
      int exitValue = process.waitFor();
      if (exitValue == 0) {
        System.out.println("Python script executed successfully");
      } else {
        System.out.println("Python script execution failed");
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

  @RequestMapping(
      value = "/predict",
      method = RequestMethod.POST
  )
  Float getPredict(@RequestParam Float temperature,
                   @RequestParam Float humidity,
                   @RequestParam Float lightIntensity,
                   @RequestParam  Float windSpeed) {
    try {
      ProcessBuilder pb = new ProcessBuilder("D:/ProgramData/Anaconda3/envs/lightning/python.exe",
          "D:/dev/lightning/backend/src/main/py_script/model_using_new.py",
          Float.toString(temperature),
          Float.toString(humidity),
          Float.toString(lightIntensity),
          Float.toString(windSpeed));
      pb.redirectErrorStream(true);
      Process process = pb.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
        return Float.parseFloat(line);
      }
      int exitValue = process.waitFor();
      if (exitValue == 0) {
        System.out.println("Python script executed successfully");
      } else {
        System.out.println("Python script execution failed");
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

}
