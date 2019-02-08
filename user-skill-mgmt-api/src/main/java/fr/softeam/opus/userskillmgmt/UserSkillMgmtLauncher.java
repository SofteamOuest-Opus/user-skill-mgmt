package fr.softeam.opus.userskillmgmt;

import io.vertx.core.Launcher;

public class UserSkillMgmtLauncher {

    public static void main(String[] args) {

        Launcher.executeCommand("run", UserSkillMgmtVerticle.class.getName());
    }

}
