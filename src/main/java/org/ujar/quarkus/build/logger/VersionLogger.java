package org.ujar.quarkus.build.logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.runtime.Startup;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Startup
@Slf4j
public class VersionLogger {

  private static final String GREEN = "\u001B[32m";

  private static final String RESET = "\u001B[0m";

  @PostConstruct
  void init() {
    log.info("{}Application Version: {} {}",
        GREEN, System.getenv("BUILD_VERSION"), RESET);
  }
}
