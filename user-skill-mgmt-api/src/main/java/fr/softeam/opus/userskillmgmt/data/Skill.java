package fr.softeam.opus.userskillmgmt.data;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true, publicConverter = false)
public class Skill {

    public enum Level{
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
        LEVEL_4,
        LEVEL_5
    }

    private String id;

    private String label;

    private Level level;

    private Category category;

    public Skill() {
        category = new Category();
    }

    public Skill(String id, String label, Level level, Category category) {
        this.id = id;
        this.label = label;
        this.level = level;
        this.category = category;
    }

    public Skill(JsonObject json) {
        SkillConverter.fromJson(json, this);
    }

    public static void toJson(Skill skill, JsonObject json){
        SkillConverter.toJson(skill, json);
    }

    public String getId() {
        return id;
    }

    @Fluent
    public Skill setId(String id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    @Fluent
    public Skill setLabel(String label) {
        this.label = label;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    @Fluent
    public Skill setLevel(Level level) {
        this.level = level;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    @Fluent
    public Skill setCategory(Category category) {
        this.category = category;
        return this;
    }
}
