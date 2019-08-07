package fr.softeam.opus.userskillmgmt.business.employee;

import fr.softeam.opus.userskillmgmt.data.Employee;
import fr.softeam.opus.userskillmgmt.services.EmployeeService;
import io.reactiverse.elasticsearch.client.RestHighLevelClient;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;

public class EmployeeBlo implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    RestHighLevelClient client;

    public EmployeeBlo(RestHighLevelClient client) {
        this.client = client;
    }

    @Override
    public void getEmployeeById(String employeeId, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
        LOGGER.debug("ENTERING getEmployeeById with param " + employeeId);
        // Creation de la requête elasticsearch avec les params : index, document et id
        GetRequest getRequest = new GetRequest("skill_mgmt", "skills", employeeId);
        // Exécution de la requête en async
        client.getAsync(getRequest, RequestOptions.DEFAULT, ar -> {
            // on traite l'employé si la requête s'est exécuté correctement
            if (ar.succeeded()) {
                // Si le resultat contient des informations on le renvoie en format json
                if (ar.result().isExists()) {
                    LOGGER.debug("result" + ar.result().getSourceAsString());
                    Employee employee = new Employee(new JsonObject(ar.result().getSourceAsString()));
                    resultHandler.handle(Future.succeededFuture(
                            OperationResponse.completedWithJson(JsonObject.mapFrom(employee))
                    ));
                } else {
                    // Si le résultat ne contient pas des informations en renvoie un 404
                    LOGGER.warn("Employee with ID " + employeeId + " not found on index.");
                    resultHandler.handle(Future.succeededFuture(
                            new OperationResponse().setStatusCode(404)
                                    .setStatusMessage("Employee with ID " + employeeId + " not found.")
                    ));
                }
            } else {
                LOGGER.error("An error occurred searching the employee.");
                resultHandler.handle(Future.failedFuture("An error occurred searching the employee. " +
                        "Please try again later... Or never, whatever."));
            }
        });

        LOGGER.debug("EXITING getEmployeeById with param " + employeeId);
    }
}
