package org.iqkv.quarkus.build.filter;

import io.quarkus.vertx.web.RouteFilter;
import io.vertx.ext.web.RoutingContext;

/**
 * Addresses <a href="https://github.com/quarkusio/quarkus/issues/6096">...</a>
 */
public class MetricsEndpointAuthorizationFilter {

  @RouteFilter(9000)
  void filters(final RoutingContext routingContext) {
    final String path = routingContext.request().path();
    if ("/metrics".equals(path) || "/q/metrics".equals(path)) {
      routingContext.request().headers().remove("Authorization");
    }
    routingContext.next();
  }
}
