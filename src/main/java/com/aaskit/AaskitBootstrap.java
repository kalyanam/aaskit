package com.aaskit;

import com.aaskit.dao.DAOFactory;
import com.aaskit.db.DBHelper;
import com.aaskit.db.DBMigrator;
import com.aaskit.handlers.*;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mkalyan on 8/15/16.
 */
public class AaskitBootstrap {
    private static final Logger logger = LoggerFactory.getLogger(AaskitBootstrap.class);

    private DAOFactory factory = null;

    private void start() {
        doMigrate();
        doStartVertx();
    }

    private void doMigrate() {
        logger.info("Starting the DBHelper");

        new DBMigrator().migrate();

        factory = DAOFactory.newBuilder()
                .withDriverName("org.postgresql.Driver")
                .withDbUrl(System.getenv("JDBC_DATABASE_URL"))
                .build();

        logger.info("DB Setup is done, starting the REST server");
    }

    private void doStartVertx() {
        logger.info("Starting up the vertx server...");
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.route().handler(CorsHandler.create("*"));

        router.get().path("/message").handler(this::getMessage);
        router.post().path("/user").handler(this::createUser);
        router.post().path("/login").handler(this::loginUser);

        //Admin APIs
        router.post().path("/admin/employer").handler(this::createEmployer);
        router.get().path("/admin/employer").handler(this::getAllEmployers);

        router.post().path("/admin/posting").handler(this::createPosting);
        router.get().path("/admin/posting").handler(this::getAllPostings);

        router.get().path("/*").handler(StaticHandler.create()
                .setAllowRootFileSystemAccess(true)
//                .setWebRoot("/Users/mkalyan/dev/workspace/aaskit/src/main/resources/public")
                .setWebRoot("public")
                .setCachingEnabled(false));

        Integer port = Integer.valueOf(System.getenv("PORT"));
        vertx.createHttpServer().requestHandler(router::accept).listen(port);

        logger.info("Vertx server listening on port: {}", port);
    }

    private void getMessage(RoutingContext routingContext) {
        routingContext.response().end("SriHari bol bol!");
    }

    private void createUser(RoutingContext routingContext) {
        new CreateNewUser(factory).handle(routingContext);
    }

    private void loginUser(RoutingContext routingContext) {
        new LogUserIn(factory.getLoginDetailsDAO()).handle(routingContext);
    }

    private void createEmployer(RoutingContext routingContext) {
        new CreateNewEmployer(factory.getEmployerDAO()).handle(routingContext);
    }

    private void getAllEmployers(RoutingContext routingContext) {
        new GetAllEmployers(factory.getEmployerDAO()).handle(routingContext);
    }

    private void createPosting(RoutingContext routingContext) {
        new CreateNewPosting(factory.getPostingDAO()).handle(routingContext);
    }

    private void getAllPostings(RoutingContext routingContext) {
        new GetAllPostings(factory.getPostingDAO()).handle(routingContext);
    }

    public static void main(String[] args) {
        AaskitBootstrap bootstrap = new AaskitBootstrap();
        bootstrap.start();
    }
}
