package fr.softeam.opus.skillusermgmt.resources;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldVerticle.class);

    @Override
    public void start() throws Exception {
        super.start();
        vertx.eventBus()
                .<String>consumer(VertxConsumersEnum.SAY_HELLO.name())
                .handler(sayHello());
    }

    private Handler<Message<String>> sayHello() {
        return msg -> vertx.<String>executeBlocking(future -> {
            try {
                future.complete(Json.encodePrettily("Hello World"));
            } catch (EncodeException e) {
                System.out.println("Failed to serialize result");
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                msg.reply(result.result());
            } else {
                msg.reply(result.cause()
                        .toString());
            }
        });
    }


}
