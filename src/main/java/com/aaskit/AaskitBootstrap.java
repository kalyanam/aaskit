package com.aaskit;

import static spark.SparkBase.*;
import static spark.Spark.*;

/**
 * Created by mkalyan on 8/15/16.
 */
public class AaskitBootstrap {
    public static void main(String[] args) {
        port(Integer.valueOf(8080));
        staticFileLocation("/public");

        get("/message", (req, res) -> {return "Sri Haribol!";});
    }
}
