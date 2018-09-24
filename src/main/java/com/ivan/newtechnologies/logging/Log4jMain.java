package com.ivan.newtechnologies.logging;

import org.apache.log4j.Logger;

public class Log4jMain {
    private static final Logger logger = Logger.getLogger(Log4jMain.class);

    public static void main(String[] args) {
        logger.trace("It is a trace message");
        logger.debug("It is a debug message");
        logger.info("It is a info message");
        logger.warn("It is a warn message");
        logger.error("It is a error message");
        logger.fatal("It is a fatal message");
    }

}
