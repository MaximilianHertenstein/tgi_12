package org.example;

import io.javalin.config.JavalinConfig;
import io.javalin.config.RoutesConfig;

public class JavalinConfigurator {

    public static void configure(JavalinConfig config) {
        var controller = new Controller();
        RoutesConfig routes = config.routes;
        routes.get("/pathToHelloWorld", controller::respondHelloWorld);
    }
}
