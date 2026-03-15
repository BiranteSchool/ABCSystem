package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Singleton Logger pour journaliser toutes les opérations, traitements et exceptions
 */
public class Logger {
    private static Logger instance;
    private DateTimeFormatter formatter;

    private Logger() {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void info(String message) {
        log("INFO", message);
    }

    public void warning(String message) {
        log("WARNING", message);
    }

    public void error(String message) {
        log("ERROR", message);
    }

    public void exception(String message, Exception e) {
        log("EXCEPTION", message + " - " + e.getMessage());
    }

    private void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[" + timestamp + "] [" + level + "] " + message);
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
}
