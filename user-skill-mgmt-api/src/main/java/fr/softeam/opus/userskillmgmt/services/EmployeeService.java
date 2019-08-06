package fr.softeam.opus.userskillmgmt.services;

import fr.softeam.opus.userskillmgmt.business.employee.EmployeeBlo;
import io.reactiverse.elasticsearch.client.RestHighLevelClient;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import io.vertx.ext.web.api.generator.WebApiServiceGen;

@WebApiServiceGen
public interface EmployeeService {

    void getEmployeeById(String employeeId,
                         OperationRequest context,
                         Handler<AsyncResult<OperationResponse>> resultHandler);
}
