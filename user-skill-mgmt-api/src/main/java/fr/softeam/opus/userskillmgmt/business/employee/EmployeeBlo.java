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

public class EmployeeBlo implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    RestHighLevelClient client;

    public EmployeeBlo(RestHighLevelClient client) {
        this.client = client;
    }

    @Override
    public void getEmployeeById(String employeeId, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
        LOGGER.debug("ENTERING getEmployeeById with param " + employeeId);
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(employee))));
        LOGGER.debug("EXITING getEmployeeById with param " + employeeId);
    }
}
