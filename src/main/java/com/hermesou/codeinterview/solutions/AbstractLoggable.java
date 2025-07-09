package com.hermesou.codeinterview.solutions;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractLoggable {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    // Concrete logging, can be overridden in subclasses
    protected boolean logging = Solution.LOGGING;
    protected Level logLevel = Solution.LOG_LEVEL;

    protected void setLogging(boolean logging) {
        this.logging = logging;
    }

    protected void setLogLevel(Level logLevel) {
        this.logLevel = logLevel != null ? logLevel : Level.OFF;
    }

    protected void log(Object object) {
        if (object != null)
            log(object::toString);
    }

    protected void log(Supplier<String> supplier) {
        if (isLogging())
            logger.logp(logLevel, getClass().getSimpleName(), "solution", supplier.get());
    }

    protected boolean isLogging() {
        return logging && logLevel != Level.OFF;
    }

}