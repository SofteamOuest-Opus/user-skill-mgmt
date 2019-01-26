package fr.softeam.opus.userskillmgmt.services;

import fr.softeam.opus.userskillmgmt.services.impl.HelloManagerServiceImpl;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import io.vertx.ext.web.api.generator.WebApiServiceGen;

@WebApiServiceGen
public interface HelloManagerService {
    static HelloManagerService create() {
        return new HelloManagerServiceImpl();
    }

    void sayHello(OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);


}
