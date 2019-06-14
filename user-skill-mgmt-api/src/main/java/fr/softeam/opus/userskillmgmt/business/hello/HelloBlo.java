package fr.softeam.opus.userskillmgmt.business.hello;

import com.hubrick.vertx.elasticsearch.ElasticSearchService;
import com.hubrick.vertx.elasticsearch.model.GetOptions;
import fr.softeam.opus.userskillmgmt.services.HelloService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;

import javax.inject.Inject;

public class HelloBlo implements HelloService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloBlo.class);

    @Inject
    private ElasticSearchService elasticSearchService;


    @Override
    public void sayHello(String nom, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
        LOGGER.info("Say Hello to " + nom);

        final HelloDTO helloDTO = new HelloDTO();
        helloDTO.setMessage("HelloDTO " + nom);


        resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(helloDTO))));
    }
}