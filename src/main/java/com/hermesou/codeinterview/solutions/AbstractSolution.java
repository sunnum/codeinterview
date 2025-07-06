package com.hermesou.codeinterview.solutions;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractSolution {

    // Concrete logging, can be overridden in subclasses
    protected static boolean LOGGING = Solution.LOGGING;
    protected static Level LOG_LEVEL = Solution.LOG_LEVEL;

    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected void log(Object object) {
        if (object != null)
            log(object::toString);
    }

    protected void log(Supplier<String> supplier) {
        if (isLogging())
            logger.logp(LOG_LEVEL, getClass().getSimpleName(), "solution", supplier.get());
    }

    protected boolean isLogging() {
        return LOGGING && LOG_LEVEL != null && LOG_LEVEL != Level.OFF;
    }

}