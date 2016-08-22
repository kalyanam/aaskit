package com.aaskit.handlers;

import com.aaskit.dao.EmployerDAO;
import com.aaskit.dao.PostingDAO;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by mkalyan on 8/22/16.
 */
public class GetAllEmployers {
    private static final Logger logger = LoggerFactory.getLogger(GetAllEmployers.class);
    private EmployerDAO employerDAO;

    public GetAllEmployers(EmployerDAO employerDAO) {
        this.employerDAO = employerDAO;
    }

    public void handle(RoutingContext routingContext) {
        List<Map<String, Object>> data = employerDAO.findAllEmployers();

        routingContext.response().setStatusCode(HttpStatus.OK_200).end(Json.encode(data));
    }
}
