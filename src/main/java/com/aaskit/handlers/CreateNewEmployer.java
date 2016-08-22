package com.aaskit.handlers;

import com.aaskit.dao.EmployerDAO;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mkalyan on 8/22/16.
 */
public class CreateNewEmployer {
    private static final Logger logger = LoggerFactory.getLogger(CreateNewEmployer.class);
    private EmployerDAO employerDAO;

    public CreateNewEmployer(EmployerDAO employerDAO) {
        this.employerDAO = employerDAO;
    }

    public void handle(RoutingContext routingContext) {
        String body = routingContext.getBodyAsJson().encodePrettily();
        logger.info("Body is: {}", body);
        JsonObject jsonObject = new JsonObject(body);

        employerDAO.createNewEmployer(jsonObject.getString("name"), jsonObject.getString("location"), jsonObject.getString("website"));

        routingContext.response().setStatusCode(HttpStatus.CREATED_201).end("{ \"status\": \"Success\" }");

    }
}
