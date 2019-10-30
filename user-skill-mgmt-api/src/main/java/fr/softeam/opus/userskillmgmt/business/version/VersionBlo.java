package fr.softeam.opus.userskillmgmt.business.version;

import fr.softeam.opus.userskillmgmt.services.VersionService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;

public class VersionBlo implements VersionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VersionBlo.class);

    public VersionBlo() {
    }


    @Override
    public void getVersion(OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
        final VersionDTO versionDTO = new VersionDTO();
        versionDTO.setVersion("v1.0");
        resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(versionDTO))));
    }
}
