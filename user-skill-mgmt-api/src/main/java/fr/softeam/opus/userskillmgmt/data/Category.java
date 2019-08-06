package fr.softeam.opus.userskillmgmt.data;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true, publicConverter = false)
public class Category {
    private String id;

    private String label;

    public Category() {
    }

    public Category(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public Category(JsonObject json) {
        CategoryConverter.fromJson(json, this);
    }

    public static void toJson(Category category, JsonObject json){
        CategoryConverter.toJson(category, json);
    }

    public String getId() {
        return id;
    }

    @Fluent
    public Category setId(String id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    @Fluent
    public Category setLabel(String label) {
        this.label = label;
        return this;
    }
}
