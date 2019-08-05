package fr.softeam.opus.userskillmgmt;

import fr.softeam.opus.userskillmgmt.configuration.BeansBinderConfig;
import fr.softeam.opus.userskillmgmt.configuration.GuiceDeploymentHelper;
import fr.softeam.opus.userskillmgmt.verticle.UserSkillMgmtVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class UserSkillMgmtLauncher {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        GuiceDeploymentHelper deployer = new GuiceDeploymentHelper(vertx, new JsonObject(), BeansBinderConfig.class);
        deployer.deployVerticles(UserSkillMgmtVerticle.class);

        deployES(deployer);
        
        deployer.coordinateFutures();
    }

    private static void deployES(GuiceDeploymentHelper deployer) {



        final JsonObject conf = new JsonObject();
        conf.put("address", "bus.elasticsearch");

        final List<JsonObject> transportList = new ArrayList<>();
        final JsonObject transport = new JsonObject();
        transport.put("hostname", "localhost");
        transport.put("port", 9200);
        transportList.add(transport);
        conf.put("transportAddresses", transportList);

        conf.put("cluster_name","user-skill-mgmt");
        conf.put("client_transport_sniff", true);

        final JsonObject elastic = new JsonObject();
        elastic.put("elasticsearch", conf);

        deployer.deployVerticles(UserSkillMgmtVerticle.class);
//        deployer.deployVerticles("service", "com.hubrick.vertx.vertx-elasticsearch-service", elastic, BeansBinderConfig.class);
    }

}
