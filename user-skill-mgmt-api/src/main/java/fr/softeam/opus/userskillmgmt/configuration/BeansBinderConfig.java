package fr.softeam.opus.userskillmgmt.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import fr.softeam.opus.userskillmgmt.business.employee.EmployeeBlo;
import fr.softeam.opus.userskillmgmt.business.hello.HelloBlo;
import fr.softeam.opus.userskillmgmt.business.version.VersionBlo;
import fr.softeam.opus.userskillmgmt.services.EmployeeService;
import fr.softeam.opus.userskillmgmt.services.HelloService;
import fr.softeam.opus.userskillmgmt.services.VersionService;
import io.reactiverse.elasticsearch.client.RestHighLevelClient;
import io.vertx.core.Vertx;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

public class BeansBinderConfig extends AbstractModule {

    @Provides
    @Singleton
    public HelloService provideHelloService() {
        return new HelloBlo();
    }

    @Provides
    @Singleton
    public VersionService provideVersionService() {
        return new VersionBlo();
    }

    @Provides
    @Singleton
    public EmployeeService provideEmployeeService(RestHighLevelClient client){
        return new EmployeeBlo(client);
    }

    @Provides
    @Singleton
    public ConfigUtils providePropertiesUtils() {
        return new ConfigUtils();
    }

    @Provides
    @Singleton
    public RestHighLevelClient provideRestHighLevelClient(Vertx vertx){
        //ToDo set host and port from conf
        return RestHighLevelClient.create(vertx, RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        ));
    }

    @Override
    protected void configure() {

    }
}
