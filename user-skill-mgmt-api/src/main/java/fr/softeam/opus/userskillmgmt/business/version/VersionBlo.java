package fr.softeam.opus.userskillmgmt.business.version;

import com.hubrick.vertx.elasticsearch.ElasticSearchService;
import com.hubrick.vertx.elasticsearch.model.GetOptions;
import com.hubrick.vertx.elasticsearch.model.SearchOptions;
import com.hubrick.vertx.elasticsearch.model.SearchType;
import com.hubrick.vertx.elasticsearch.model.SortOrder;
import fr.softeam.opus.userskillmgmt.services.VersionService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;

import javax.inject.Inject;

public class VersionBlo implements VersionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VersionBlo.class);

    private ElasticSearchService elasticSearchService;

    public VersionBlo(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }


    @Override
    public void getVersion(OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
//        final VersionDTO versionDTO = new VersionDTO();
//        versionDTO.setVersion("v1.0");

        final SearchOptions searchOptions = new SearchOptions()
            .setQuery(new JsonObject().put("match_all", new JsonObject()))
            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
            .setFetchSource(true);


        elasticSearchService.search("user-skill-mgmt", searchOptions, response -> {
            if(response.succeeded()) {
                LOGGER.info("Search response: {}", response.result());
                JsonObject body = response.result().toJson();
                resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(body)));
            } else {
                resultHandler.handle(Future.failedFuture("Version not found"));
            }
//            resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(versionDTO))));
        });


    }
}
