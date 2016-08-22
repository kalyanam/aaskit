package com.aaskit.handlers;

import com.aaskit.dao.DAOFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mkalyan on 8/22/16.
 */
public class CreateNewUser {
    private static final Logger logger = LoggerFactory.getLogger(CreateNewUser.class);
    private DAOFactory factory;

    public CreateNewUser(DAOFactory factory) {
        this.factory = factory;
    }

    public void handle(RoutingContext routingContext) {
        String body = routingContext.getBodyAsJson().encodePrettily();
        logger.info("Body is: {}", body);
        JsonObject jsonObject = new JsonObject(body);

        factory.getUserDetailsDAO().createNewUser(jsonObject.getString("email"), jsonObject.getString("password"));

        routingContext.response().setStatusCode(HttpStatus.CREATED_201).end("{ \"status\": \"Success\" }");
    }
}
