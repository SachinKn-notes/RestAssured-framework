package libs.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* hierarchy of log4j logging

 *
 * OFF
 * FATAL
 * ERROR
 * WARN
 * INFO
 * DEBUG
 * TRACE
 * ALL
 *
 */

public class LoggerUtils {
    private LoggerUtils() {}

    private static final Logger LOGGER = LogManager.getLogger(LoggerUtils.class.getName());

    public static void logError(String message, Exception ex) {
        LOGGER.error(message, ex);
    }

    public static void logError(String message) {
        LOGGER.error(message);
    }

    public static void logInfo(String message) {
        LOGGER.info(message);
    }

    public static void logTrace(String message) {
        LOGGER.trace(message);
    }

    public static void logDebug(String message) {
        LOGGER.debug(message);
    }

    public static void logWarn(String message) {
        LOGGER.warn(message);
    }

    public static void logFatal(String message) {
        LOGGER.fatal(message);
    }
}
