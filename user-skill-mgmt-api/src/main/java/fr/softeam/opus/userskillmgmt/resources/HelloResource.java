package fr.softeam.opus.userskillmgmt.resources;

import fr.softeam.opus.userskillmgmt.configuration.EventBusEnum;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class HelloResource extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) {

        Router router = Router.router(vertx);
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
