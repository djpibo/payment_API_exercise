package util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PortoneApi implements PortalApi{
  public PortoneApi() {
    //log.info("[PortOneApi] PortOneApi API");
  }
  @Override
  public String integrate() {
    return "PortOneApi integration succeeded";
  }
}
