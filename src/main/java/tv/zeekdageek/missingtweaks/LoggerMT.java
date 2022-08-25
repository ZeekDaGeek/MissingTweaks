package tv.zeekdageek.missingtweaks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerMT
{
    public static Logger logger = LogManager.getLogger(MissingTweaks.MODID);

    private static boolean verbose = true;

    /**
     * General log helper.
     * @param message message
     */
    public static void log(String message) {
        logger.info(message);
    }

    /**
     * General log helper that only logs when verbose is on.
     * @param message
     */
    public static void vlog(String message) {
        if (!verbose)
            return;

        logger.info(message);
    }

    public static void vlog(String message, Object ... args) {
        if (!verbose)
            return;

        logger.info(message, args);
    }
}
