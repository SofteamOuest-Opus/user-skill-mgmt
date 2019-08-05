package fr.softeam.opus.userskillmgmt.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.hubrick.vertx.elasticsearch.ElasticSearchService;
import fr.softeam.opus.userskillmgmt.business.hello.HelloBlo;
import fr.softeam.opus.userskillmgmt.business.version.VersionBlo;
import fr.softeam.opus.userskillmgmt.services.HelloService;
import fr.softeam.opus.userskillmgmt.services.VersionService;
import io.vertx.core.Vertx;

public class BeansBinderConfig extends AbstractModule {

    @Provides
    @Singleton
    public HelloService provideHelloService(Vertx vertx) {
        return new HelloBlo(vertx);
    }

    @Provides
    @Singleton
    public ElasticSearchService provideElasticSearchService(Vertx vertx) {
        return ElasticSearchService.createEventBusProxy(vertx, "eventbus-address");
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
