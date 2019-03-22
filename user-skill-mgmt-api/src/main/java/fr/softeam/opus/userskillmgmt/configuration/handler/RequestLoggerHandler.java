package fr.softeam.opus.userskillmgmt.configuration.handler;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.ext.web.handler.LoggerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLoggerHandler implements LoggerHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggerHandler.class);

    /** log before request or after
     */
    private boolean immediate;

    /** the current choosen format
     */
    private LoggerFormat format;

    public RequestLoggerHandler(LoggerFormat format) {
        this.format = format;
    }

    public RequestLoggerHandler(boolean immediate, LoggerFormat format) {
        this.format = format;
        this.immediate = immediate;
    }

    public static RequestLoggerHandler create() {
        return new RequestLoggerHandler(DEFAULT_FORMAT);
    }

    public static RequestLoggerHandler create(LoggerFormat format) {
        return new RequestLoggerHandler(format);
    }

    public static RequestLoggerHandler create(boolean immediate, LoggerFormat format) {
        return new RequestLoggerHandler(immediate, format);
    }

    @Override
    public void handle(RoutingContext routingContext) {
        LOGGER.info(routingContext.getBodyAsString());
    }

}
