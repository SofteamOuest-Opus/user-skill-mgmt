package fr.softeam.opus.userskillmgmt.business;

import fr.softeam.opus.userskillmgmt.configuration.EventBusEnum;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


public class HelloWorldBlo extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldBlo.class);

    @Override
    public void start() throws Exception {
        super.start();
        vertx.eventBus()
                .<String>consumer(EventBusEnum.SAY_HELLO.name())
                .handler(sayHello());
    }

    private Handler<Message<String>> sayHello() {
        LOGGER.info("Say Hello");
        return msg -> vertx.<String>executeBlocking(future -> {
            try {
                future.complete(Json.encodePrettily("Hello New World"));
            } catch (EncodeException e) {
                LOGGER.error("Failed to serialize result");
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                LOGGER.info("Result --> {}", result.result());
                msg.reply(result.result());
            } else {
                LOGGER.error("Result --> {}", result.result());
                msg.reply(result.cause().toString());
            }
        });
    }


}
