package Utils;

import org.apache.log4j.Logger;

public class Log {
    private static final Logger logger = Logger.getLogger("TestLogger");
    public static Logger getLogger() {
        return logger;
    }
}
