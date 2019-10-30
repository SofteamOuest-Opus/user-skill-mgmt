package fr.softeam.opus.userskillmgmt.verticle;

import io.reactiverse.elasticsearch.client.RestHighLevelClient;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class ElasticSearchVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchVerticle.class);

    @Inject
    private RestHighLevelClient client;

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        String yo = "{\"foo\": \"bar\"}";
        IndexRequest req = new IndexRequest("posts", "_doc", "1").source(yo, XContentType.JSON);
        client.indexAsync(req, RequestOptions.DEFAULT, ar -> {
            LOGGER.debug("success ??" + ar);
        });
    }
}
