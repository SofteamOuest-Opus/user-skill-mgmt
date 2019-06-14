package fr.softeam.opus.userskillmgmt.configuration;

import io.vertx.core.json.JsonObject;

import java.util.Scanner;

public class ConfigUtils {
    private JsonObject config;

    public ConfigUtils() {
        this.config = readConfig();
    }

    private JsonObject readConfig() {
        return readJson("config.json");
    }

    private JsonObject readJson(String path) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        try (Scanner scanner = new Scanner(cl.getResourceAsStream(path)).useDelimiter("\\A")) {
            String s = scanner.next();
            return new JsonObject(s);
        }
    }
}
