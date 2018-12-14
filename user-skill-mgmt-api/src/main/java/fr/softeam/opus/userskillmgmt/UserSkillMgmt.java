package fr.softeam.opus.userskillmgmt;

import fr.softeam.opus.userskillmgmt.business.HelloWorldBlo;
import fr.softeam.opus.userskillmgmt.resources.HelloResource;
import io.vertx.core.Vertx;

public class UserSkillMgmt {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new HelloResource());

        vertx.deployVerticle(new HelloWorldBlo());



    }
}


