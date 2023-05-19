package cn.bupt.lightning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Thread.sleep;

public class CommandLine {
  public static void main(String[] args) {
    try {
      ProcessBuilder pb = new ProcessBuilder("D:/ProgramData/Anaconda3/envs/lightning/python.exe", "D:/dev/lightning/backend/src/main/py_new/model_using_new.py", "93", "0", "25", "5");
      pb.redirectErrorStream(true);
      Process process = pb.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
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
  }
}