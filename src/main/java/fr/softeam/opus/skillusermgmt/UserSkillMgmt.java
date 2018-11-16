package fr.softeam.opus.skillusermgmt;

import io.vertx.core.Vertx;

public class UserSkillMgmt {

    public static void main(String[] args) {
        Vertx.vertx()
                .createHttpServer()
                .requestHandler(req -> req.response().end("Hello World!"))
                .listen(8080, handler -> {
                    if (handler.succeeded()) {
                        System.out.println("http://localhost:8080/");
                    } else {
                        System.err.println("Failed to listen on port 8080");
                    }
                });
    }
}


