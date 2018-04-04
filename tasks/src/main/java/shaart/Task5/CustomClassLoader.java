package shaart.Task5;

import org.apache.log4j.Logger;

public class CustomClassLoader {
    final static Logger logger = Logger.getLogger(CustomClassLoader.class);

    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        customClassLoader.logInfo("Hello");
    }

    private void logInfo(String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }
}
