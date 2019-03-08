package fr.softeam.opus.userskillmgmt;

import fr.softeam.opus.userskillmgmt.configuration.BeansBinderConfig;
import fr.softeam.opus.userskillmgmt.configuration.GuiceDeploymentHelper;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class UserSkillMgmtLauncher {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        GuiceDeploymentHelper deployer = new GuiceDeploymentHelper(vertx, new JsonObject(), BeansBinderConfig.class);
        deployer.deployVerticles(UserSkillMgmtVerticle.class);
        deployer.coordinateFutures();
    }

}
