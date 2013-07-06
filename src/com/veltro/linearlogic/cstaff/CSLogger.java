package com.veltro.linearlogic.cstaff;

import java.util.logging.Logger;

public class CSLogger
{
  private static final Logger log = Logger.getLogger("Minecraft");

  public static void logInfo(String message)
  {
    log.info("[cStaff] " + message);
  }
  
  public static void logWarning(String message) {
    log.warning("[cStaff] " + message);
  }

  public static void logSevere(String message) {
    log.severe("[cStaff] " + message);
  }
}