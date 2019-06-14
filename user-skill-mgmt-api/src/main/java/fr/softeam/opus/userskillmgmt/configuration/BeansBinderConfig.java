package fr.softeam.opus.userskillmgmt.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.hubrick.vertx.elasticsearch.ElasticSearchAdminService;
import com.hubrick.vertx.elasticsearch.ElasticSearchService;
import com.hubrick.vertx.elasticsearch.impl.DefaultElasticSearchAdminService;
import com.hubrick.vertx.elasticsearch.internal.InternalElasticSearchAdminService;
import com.hubrick.vertx.elasticsearch.model.CreateIndexOptions;
import com.hubrick.vertx.elasticsearch.model.DeleteIndexOptions;
import com.hubrick.vertx.elasticsearch.model.MappingOptions;
import com.hubrick.vertx.elasticsearch.model.TemplateOptions;
import fr.softeam.opus.userskillmgmt.business.hello.HelloBlo;
import fr.softeam.opus.userskillmgmt.business.version.VersionBlo;
import fr.softeam.opus.userskillmgmt.services.HelloService;
import fr.softeam.opus.userskillmgmt.services.VersionService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.elasticsearch.client.AdminClient;

import java.util.List;

public class BeansBinderConfig extends AbstractModule {

    @Provides
    @Singleton
    public HelloService provideHelloService() {
        return new HelloBlo();
    }

    @Provides
    @Singleton
    public ElasticSearchService provideElasticSearchService() {
        return ElasticSearchService.createEventBusProxy(Vertx.vertx(), "eventbus-address");
    }

    @Provides
    @Singleton
    public ElasticSearchAdminService elasticSearchAdminService(){
        return new InternalElasticSearchAdminService() {
            @Override
            public AdminClient getAdmin() {
                return null;
            }

            @Override
            public void putMapping(List<String> indices, String type, JsonObject source, MappingOptions options, Handler<AsyncResult<Void>> resultHandler) {

            }

            @Override
            public void createIndex(String index, JsonObject source, CreateIndexOptions options, Handler<AsyncResult<Void>> resultHandler) {

            }

            @Override
            public void deleteIndex(List<String> indices, DeleteIndexOptions options, Handler<AsyncResult<Void>> resultHandler) {

            }

            @Override
            public void putTemplate(String name, JsonObject source, TemplateOptions options, Handler<AsyncResult<Void>> resultHandler) {

            }

            @Override
            public void deleteTemplate(String name, TemplateOptions options, Handler<AsyncResult<Void>> resultHandler) {

            }
        };
    }

    @Provides
    @Singleton
    public VersionService provideHelloBlo(ElasticSearchService elasticSearchService) {
        return new VersionBlo(elasticSearchService);
    }

    @Provides
    @Singleton
    public ConfigUtils providePropertiesUtils() {
        return new ConfigUtils();
    }



    @Override
    protected void configure() {

    }
}
