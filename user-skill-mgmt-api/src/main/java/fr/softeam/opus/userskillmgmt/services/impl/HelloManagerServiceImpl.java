package fr.softeam.opus.userskillmgmt.services.impl;

import fr.softeam.opus.userskillmgmt.services.HelloManagerService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;

public class HelloManagerServiceImpl implements HelloManagerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloManagerServiceImpl.class);


    public HelloManagerServiceImpl() {
    }


    @Override
    public void sayHello(OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
        final Hello hello = new Hello();
        hello.setMessage("Hello World");
        LOGGER.info("Say Hello");
        resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(hello))));
    }
}
