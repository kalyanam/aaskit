package com.aaskit.handlers;

import com.aaskit.dao.PostingDAO;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by mkalyan on 8/22/16.
 */
public class GetAllPostings {
    private static final Logger logger = LoggerFactory.getLogger(GetAllPostings.class);
    private PostingDAO postingDAO;

    public GetAllPostings(PostingDAO postingDAO) {
        this.postingDAO = postingDAO;
    }

    public void handle(RoutingContext routingContext) {
        List<Map<String, Object>> data = postingDAO.findAllPostings();

        routingContext.response().setStatusCode(HttpStatus.OK_200).end(Json.encode(data));
    }
}
