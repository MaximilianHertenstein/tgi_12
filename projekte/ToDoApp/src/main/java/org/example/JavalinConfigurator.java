package org.example;

import io.javalin.config.JavalinConfig;

public class JavalinConfigurator {

    public static void configureJavalin(JavalinConfig javalinConfig) {
        var serverController = new ServerController();
        var routes = javalinConfig.routes;
        routes.get("/todos", serverController::showApp);
        routes.post("/todos/new", serverController::addToDo);
        routes.post("/todos/{id}/toggleStatus", serverController::toggleStatus);
        routes.post("/todos/setFilter/{filter}", serverController::setFilter);
        routes.get("/todos/{id}/edit", serverController::showEditForm);
        routes.post("/todos/{id}/edit", serverController::updateTextOfToDo);
        routes.post("/todos/{id}/delete", serverController::deleteToDo);
        routes.post("/todos/clearCompletedToDos", serverController::clearCompletedToDos);
        javalinConfig.staticFiles.enableWebjars();
    }
}
