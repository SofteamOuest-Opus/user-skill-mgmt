package fr.softeam.opus.userskillmgmt.services;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import io.vertx.ext.web.api.generator.WebApiServiceGen;

@WebApiServiceGen
public interface VersionService {
    void getVersion(OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);
}
