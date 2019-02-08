package fr.softeam.opus.userskillmgmt;

import fr.softeam.opus.userskillmgmt.business.hello.HelloBloImpl;
import fr.softeam.opus.userskillmgmt.services.HelloService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.serviceproxy.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class UserSkillMgmtVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSkillMgmtVerticle.class);

//    @Inject
//    private HelloService helloService;

    HttpServer server;
    ServiceBinder serviceBinder;
    MessageConsumer<JsonObject> consumer;


    @Override
    public void start(Future<Void> future) {
        LOGGER.info("Starting User Skill MGMT verticle.");
        startHelloService();
        startHttpServer().setHandler(future.completer());
    }

    /**
     * This method closes the http server and unregister all services loaded to Event Bus
     */
    @Override
    public void stop(){
        this.server.close();
        consumer.unregister();
    }

    private void startHelloService() {
        serviceBinder = new ServiceBinder(vertx);

        consumer = serviceBinder
                .setAddress("hello_service.usm")
                .register(HelloService.class, new HelloBloImpl());

    }

    private Future<Void> startHttpServer() {
        Future<Void> future = Future.future();
        OpenAPI3RouterFactory.create(this.vertx, "/openapi.yaml", openAPI3RouterFactoryAsyncResult -> {
            if (openAPI3RouterFactoryAsyncResult.succeeded()) {
                OpenAPI3RouterFactory routerFactory = openAPI3RouterFactoryAsyncResult.result();

                routerFactory.mountServicesFromExtensions();

                Router router = routerFactory.getRouter();
                server = vertx.createHttpServer(new HttpServerOptions().setPort(8080).setHost("localhost"));
                server.requestHandler(router).listen(ar -> {
                    // Error starting the HttpServer
                    if (ar.succeeded()) future.complete();
                    else future.fail(ar.cause());
                });
            } else {
                // Something went wrong during router factory initialization
                future.fail(openAPI3RouterFactoryAsyncResult.cause());
            }
        });
        return future;
    }
}


