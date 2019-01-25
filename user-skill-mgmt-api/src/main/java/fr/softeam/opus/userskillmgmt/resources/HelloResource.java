package fr.softeam.opus.userskillmgmt.resources;

import com.mdac.vertx.web.accesslogger.appender.printstream.impl.PrintStreamAppenderOptions;
import com.mdac.vertx.web.accesslogger.impl.AccessLoggerOptions;
import fr.softeam.opus.userskillmgmt.configuration.EventBusEnum;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import com.mdac.vertx.web.accesslogger.AccessLoggerHandler;
import com.mdac.vertx.web.accesslogger.appender.logging.impl.LoggingAppenderOptions;

import java.util.Arrays;

public class HelloResource extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) {

        Router router = Router.router(vertx);

        router
            .route()
            .handler(AccessLoggerHandler.create(new AccessLoggerOptions().setPattern("%t %m %U %q %s %D"),
                    Arrays.asList(
//                            new PrintStreamAppenderOptions().setPrintStream(System.out),
                            new LoggingAppenderOptions()
                                    .setLoggerName("accesslog")
                    )
                    )
            );

        router.get("/hello").handler(this::sayHello);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config().getInteger("http.port", 8080), result -> {
                    if (result.succeeded()) {
                        future.complete();
                    } else {
                        future.fail(result.cause());
                    }
                });
    }

    private void sayHello(RoutingContext routingContext) {

        vertx.eventBus()
                .<String>send(EventBusEnum.SAY_HELLO.name(), routingContext.getBodyAsJson(), result -> {
                    if (result.succeeded()) {
                        routingContext.response()
                                .putHeader("content-type", "application/json")
                                .putHeader("Access-Control-Allow-Origin", "*")
                                .putHeader("Access-Control-Allow-Methods", "GET, PUT, PATCH, POST, DELETE, HEAD")
                                .putHeader("Access-Control-Allow-Headers", "*")
                                .putHeader("Access-Control-Max-Age", "86400")
                                .setStatusCode(200)
                                .end(result.result()
                                        .body());
                    } else {
                        routingContext.response()
                                .setStatusCode(500)
                                .end();
                    }
                });
    }


}
