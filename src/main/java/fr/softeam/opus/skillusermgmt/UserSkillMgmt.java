package fr.softeam.opus.skillusermgmt;

import fr.softeam.opus.skillusermgmt.resources.HelloWorldVerticle;
import fr.softeam.opus.skillusermgmt.resources.ServerVerticle;
import io.vertx.core.Vertx;

public class UserSkillMgmt {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new HelloWorldVerticle());

        vertx.deployVerticle(new ServerVerticle());

    }
}


