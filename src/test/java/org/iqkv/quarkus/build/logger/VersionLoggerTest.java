package org.iqkv.quarkus.build.logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VersionLoggerTest {

  private static final VersionLogger CUT = new VersionLogger();

  @Test
  void test() {
    CUT.init();
  }
}
