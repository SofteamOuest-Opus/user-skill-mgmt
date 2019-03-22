package fr.softeam.opus.userskillmgmt.verticle;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreationCollaborateurVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreationCollaborateurVerticle.class);

    @Override
    public void start() throws Exception {

//        // Get the Kafka consumer config
//        JsonObject config = config();
//
//        // Create the consumer
//        KafkaReadStream<String, JsonObject> consumer = KafkaReadStream.create(vertx, config.getMap(), String.class, JsonObject.class);
//
//        // Aggregates metrics in the dashboard
//        consumer.handler(record -> {
//            JsonObject obj = record.value();
//            LOGGER.info(obj.toString());
//        });
//
//        // Subscribe to Kafka
//        consumer.subscribe(Collections.singleton("CREATION_COLLAB"));

    }

    @Override
    public void stop() throws Exception {

    }
}
