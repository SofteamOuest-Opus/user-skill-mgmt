package fr.softeam.opus.skillusermgmt.resources;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class ServerVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) {

        Router router = Router.router(vertx);
//        router.post("/api/*").handler(BodyHandler.create());
//        router.put("/api/*").handler(BodyHandler.create());
//        router.patch("/api/*").handler(BodyHandler.create());
//        router.delete("/api/*").handler(BodyHandler.create());

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

//        Vertx vertx = Vertx.vertx();

        vertx.eventBus()
                .<String>send(VertxConsumersEnum.SAY_HELLO.name(), routingContext.getBodyAsJson(), result -> {
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
