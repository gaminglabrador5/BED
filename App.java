// This is the Logger interface (a set of rules).
// Any class that implements Logger must have a log and error method.
interface Logger {
    void log(String message);
    void error(String message);
}

// This class logs messages with asterisks.
// For example: ***Hello*** or a box around "Error: Hello"
class AsteriskLogger implements Logger {
    @Override
    public void log(String message) {
        // Prints the message with three asterisks before and after
        System.out.println("***" + message + "***");
    }

    @Override
    public void error(String message) {
        // Prepares the error message with asterisks and "Error: "
        String errorMessage = "***Error: " + message + "***";
        String stars = "";
        // Creates a line of asterisks as long as the error message
        for (int i = 0; i < errorMessage.length(); i++) {
            stars += "*";
        }
        // Prints the top border, error message, and bottom border
        System.out.println(stars);
        System.out.println(errorMessage);
        System.out.println(stars);
    }
}

// This class logs messages with spaces between each character.
// For example: H e l l o
class SpacedLogger implements Logger {
    @Override
    public void log(String message) {
        // Builds a string with spaces between each character
        String result = "";
        for (int i = 0; i < message.length(); i++) {
            result += message.charAt(i);
            // Only add a space if it's not the last character
            if (i < message.length() - 1) {
                result += " ";
            }
        }
        // Prints the spaced-out message
        System.out.println(result);
    }

    @Override
    public void error(String message) {
        // Builds a string starting with "ERROR: " and spaces between each character
        String result = "ERROR: ";
        for (int i = 0; i < message.length(); i++) {
            result += message.charAt(i);
            if (i < message.length() - 1) {
                result += " ";
            }
        }
        // Prints the error message with spaces
        System.out.println(result);
    }
}

// This is the main class with the entry point (main method).
// It creates and tests both logger classes.
public class App {
    public static void main(String[] args) {
        // Creates an instance of each logger
        Logger asteriskLogger = new AsteriskLogger();
        Logger spacedLogger = new SpacedLogger();

        // Tests AsteriskLogger's log and error methods
        System.out.println("AsteriskLogger log:");
        asteriskLogger.log("Hello");
        System.out.println("AsteriskLogger error:");
        asteriskLogger.error("Hello");

        // Tests SpacedLogger's log and error methods
        System.out.println("\nSpacedLogger log:"); // <-- fixed this line
        spacedLogger.log("Hello");
        System.out.println("SpacedLogger error:");
        spacedLogger.error("Hello");
    }
}
