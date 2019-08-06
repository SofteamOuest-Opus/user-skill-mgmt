package fr.softeam.opus.userskillmgmt.business.hello;

import fr.softeam.opus.userskillmgmt.services.HelloService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;

public class HelloBlo implements HelloService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloBlo.class);

    public HelloBlo() {
    }

    @Override
    public void sayHello(String nom, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
        LOGGER.debug("Say Hello to " + nom);

        final HelloDTO helloDTO = new HelloDTO();
        helloDTO.setMessage("HelloDTO " + nom);

        resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(helloDTO))));
    }
}