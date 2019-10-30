package fr.softeam.opus.userskillmgmt.verticle;

import fr.softeam.opus.userskillmgmt.configuration.BeansBinderConfig;
import fr.softeam.opus.userskillmgmt.configuration.GuiceDeploymentHelper;
import fr.softeam.opus.userskillmgmt.data.Employee;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class UserSkillMgmtVerticleTest {
    private Vertx vertx;
    private final String host = "localhost";
    private final Integer port = 8080;
    private final String idExistent = "1";
    private final String idInexistent = "3";
    private String apiAddress = "/api/employees/";


    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        GuiceDeploymentHelper deployer = new GuiceDeploymentHelper(vertx, new JsonObject(), BeansBinderConfig.class);
        deployer.deployVerticle(
                UserSkillMgmtVerticle.class,
                context.asyncAssertSuccess()
        );
    }

    @After
    public void tearDown(TestContext context){
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void checkThatEmployeeIsReturned(TestContext context) {
        Async async = context.async();
        vertx.createHttpClient().getNow(port, host, apiAddress + idExistent, response -> {
            context.assertEquals(200, response.statusCode());
            context.assertEquals("application/json", response.getHeader("content-type"));
            response.bodyHandler(body -> {
                Employee employee = new Employee(body.toJsonObject());
                context.assertEquals(idExistent, employee.getEmployeeId());
                context.assertNotNull(body);
                context.assertTrue(employee.getSkills().size() > 0);
                async.complete();
            });
        });
    }

    @Test
    public void checkThatEmployeeDoesntExists(TestContext context){
        Async async = context.async();
        vertx.createHttpClient().getNow(port, host, apiAddress + idInexistent, response -> {
            context.assertEquals(404, response.statusCode());
            async.complete();
        });
    }


}
