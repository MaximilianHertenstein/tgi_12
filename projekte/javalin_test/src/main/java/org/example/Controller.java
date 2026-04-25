package org.example;

import io.javalin.http.Context;

public class Controller {
    public void respondHelloWorld(Context ctx){

        ctx.contentType("text/html");

        ctx.result("Keine ToDos");
    }
}
