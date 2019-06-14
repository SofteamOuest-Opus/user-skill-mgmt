package fr.softeam.opus.userskillmgmt.configuration;

import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.service.ServiceVerticleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GuiceDeploymentHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuiceDeploymentHelper.class);

    private List<Future> futureList = new ArrayList<Future>();
    private Vertx vertx;

    public GuiceDeploymentHelper(Vertx vertx, JsonObject config, Class binder) {
        this.vertx = vertx;
        this.vertx.registerVerticleFactory(new ServiceVerticleFactory());
    }

    public void deployVerticles(String prefix, String verticleName, JsonObject config, Class binder) {
        Future<String> future = Future.future();
        futureList.add(future);
        config.put("guice_binder", binder.getName());
        String deploymentName = prefix + ":" + verticleName;

        DeploymentOptions options = new DeploymentOptions();
        options.setConfig(config);

        vertx.deployVerticle(deploymentName, options, future.completer());
    }

    public void coordinateFutures(Future<Void> startFuture) {
        if (futureList.size() == 0) {
            LOGGER.warn("No Verticle deployment to do...");
            return;
        }
        CompositeFuture.all(futureList).setHandler(ar -> {

            if (ar.succeeded()) {
                LOGGER.info("Verticles deployed successfully.");
                if (startFuture != null) {
                    startFuture.complete();
                }
            } else {
                LOGGER.error("WARNINIG: Verticles NOT deployed successfully: " + ar.cause());
                if (startFuture != null) {
                    startFuture.fail(ar.cause());
                }
            }
        });
    }

    public void coordinateFutures() {
        coordinateFutures(null);
    }

}
