package com.aaskit.handlers;

import com.aaskit.dao.DAOFactory;
import com.aaskit.dao.LoginDetailsDAO;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mkalyan on 8/25/16.
 */
public class LogUserIn {
    private static final Logger logger = LoggerFactory.getLogger(LogUserIn.class);
    private LoginDetailsDAO loginDetailsDAO;

    public LogUserIn(LoginDetailsDAO loginDetailsDAO) {
        this.loginDetailsDAO = loginDetailsDAO;
    }

    public void handle(RoutingContext routingContext) {
        String body = routingContext.getBodyAsJson().encodePrettily();
        logger.info("Body is: {}", body);
        JsonObject jsonObject = new JsonObject(body);

        boolean status = loginDetailsDAO.isValidUser(jsonObject.getString("email"), jsonObject.getString("password"));
        if(status) {
            routingContext.response().setStatusCode(HttpStatus.CREATED_201).end("{ \"status\": \"Success\" }");
        } else {
            routingContext.response().setStatusCode(HttpStatus.UNAUTHORIZED_401).end();
        }
    }
}
