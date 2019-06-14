package fr.softeam.opus.userskillmgmt;

import com.hubrick.vertx.elasticsearch.ElasticSearchAdminService;
import com.hubrick.vertx.elasticsearch.ElasticSearchServiceVerticle;
import com.hubrick.vertx.elasticsearch.impl.DefaultElasticSearchAdminService;
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

		deployUserSkillMgmt(deployer);
		deployES(deployer);

		deployer.coordinateFutures();
	}

	private static void deployUserSkillMgmt(GuiceDeploymentHelper deployer) {
		final JsonObject confUserSkill = new JsonObject();
		deployer.deployVerticles("java-guice", "fr.softeam.opus.userskillmgmt.verticle.UserSkillMgmtVerticle", confUserSkill, BeansBinderConfig.class);
	}

	private static void deployES(GuiceDeploymentHelper deployer) {

		final List<JsonObject> transportList = new ArrayList<>();
		final JsonObject transport = new JsonObject();
		transport.put("hostname", "localhost");
		transport.put("port", 9200);
		transportList.add(transport);
		final JsonObject elastic = new JsonObject();
		elastic.put("cluster_name", "user-skill-mgmt");
		elastic.put("address", "bus.elasticsearch");
		elastic.put("client_transport_sniff", "bus.true");
		elastic.put("transportAddresses", transportList);

		deployer.deployVerticles("java-guice", "com.hubrick.vertx.elasticsearch.ElasticSearchServiceVerticle", elastic, BeansBinderConfig.class);
	}

}
