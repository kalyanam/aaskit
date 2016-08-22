package com.aaskit.handlers;

import com.aaskit.dao.PostingDAO;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mkalyan on 8/22/16.
 */
public class CreateNewPosting {
    private static final Logger logger = LoggerFactory.getLogger(CreateNewPosting.class);
    private PostingDAO postingDAO;

    public CreateNewPosting(PostingDAO postingDAO) {
        this.postingDAO = postingDAO;
    }

    public void handle(RoutingContext routingContext) {
        String body = routingContext.getBodyAsJson().encodePrettily();
        logger.info("Body is: {}", body);
        JsonObject jsonObject = new JsonObject(body);

        postingDAO.createNewPosting(jsonObject.getInteger("employerId"), jsonObject.getString("location"),
                jsonObject.getString("jobDescription"), jsonObject.getString("primarySkills"));

        routingContext.response().setStatusCode(HttpStatus.CREATED_201).end("{ \"status\": \"Success\" }");
    }
}
