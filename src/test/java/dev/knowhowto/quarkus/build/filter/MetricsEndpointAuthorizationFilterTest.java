package dev.knowhowto.quarkus.build.filter;

import static org.junit.jupiter.api.Assertions.assertNull;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MetricsEndpointAuthorizationFilterTest {

  private static final MetricsEndpointAuthorizationFilter CUT = new MetricsEndpointAuthorizationFilter();

  @Mock
  RoutingContext context;

  @Mock
  HttpServerRequest request;

  MultiMap setup(final String path) {
    final MultiMap map = MultiMap.caseInsensitiveMultiMap();
    map.add("Authorization", "Bearer thisisafakejwt");
    Mockito.doReturn(path).when(request).path();
    Mockito.doReturn(request).when(context).request();
    Mockito.doReturn(map).when(request).headers();
    return map;
  }

  @Test
  void testMetricsPath() {
    final MultiMap map = setup("/metrics");
    CUT.filters(context);
    assertNull(map.get("Authorization"));
  }

  @Test
  void testQMetricsPath() {
    final MultiMap map = setup("/q/metrics");
    CUT.filters(context);
    assertNull(map.get("Authorization"));
  }

  @Test
  void testNonMetricsPath() {
    Mockito.doReturn("/foo").when(request).path();
    Mockito.doReturn(request).when(context).request();
    CUT.filters(context);
  }
}
