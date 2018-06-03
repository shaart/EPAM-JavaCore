package shaart.task5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import lombok.extern.log4j.Log4j;

/**
 * Task 5 Classloading <br> 1. Create a console application with possibility of dynamic
 * loading/updating development functionality. <br> 2. Implement a custom classloader with a
 * class-caching mechanism. <br> 3. Keep new functionality in a specified directory. Archive it in
 * jar. <br> 4. The application should have console menu to choose an option (add new/update
 * existing class, demonstrate new functionality - invoke methods of the new class). <br> 5.
 * Implement the output via log4j library. <br>
 */
@Log4j
public class Application {

  enum Menu {ADD_OR_UPDATE_CLASS, INVOKE_CLASS_METHOD, EXIT}

  private static final Scanner scanner = new Scanner(System.in);
  private static final ClassLoader customClassLoader = RunnableClassLoader.getInstance();
  private static final Map<String, Class> classes = new HashMap<>();

  public static void main(String[] args) {
    Menu[] menuValues = Menu.values();

    String userInput;
    int userOption;
    final String menu = "Menu\n" +
        "---------\n" +
        Menu.ADD_OR_UPDATE_CLASS.ordinal() + ". Add/Update class\n" +
        Menu.INVOKE_CLASS_METHOD.ordinal() + ". Invoke class methods\n" +
        "---------\n" +
        Menu.EXIT.ordinal() + ". Exit\n";
    boolean working = true;
    log.info(menu);

    while (working) {
      log.info("Enter menu option:");
      userInput = scanner.nextLine();
      log.debug("Got option: " + userInput);
      try {
        userOption = Integer.parseInt(userInput);
      } catch (NumberFormatException e) {
        log.error("Incorrect input");
        continue;
      }

      switch (menuValues[userOption]) {
        case ADD_OR_UPDATE_CLASS:
          loadClassInterface();
          break;
        case INVOKE_CLASS_METHOD:
          invokeClassMethodsInterface();
          break;
        case EXIT:
          working = false;
          break;
        default:
          log.info("Unknown menu option");
          break;
      }
    }
  }

  private static void invokeClassMethodsInterface() {
    log.info("Input class name: ");
    String userInput = scanner.nextLine();
    log.info("Got input: " + userInput);
    try {
      log.info("Getting specified class...");
      Class<?> loadClass = classes.get(userInput);
      if (loadClass == null) {
        log.info("Class not loaded.");
        return;
      }
      log.info("Creating instance of specified class...");
      Runnable instance = (Runnable) loadClass.newInstance();
      log.info("Calling instance's run()...");
      instance.run();
    } catch (InstantiationException | IllegalAccessException e) {
      log.error(e);
    }
  }

  private static void loadClassInterface() {
    log.info("Input class name: ");
    String userInput = scanner.nextLine();
    log.info("Got input: " + userInput);
    try {
      Class<?> loadClass = customClassLoader.loadClass(userInput);
      classes.put(loadClass.getName(), loadClass);
      log.trace(loadClass.getCanonicalName() + " added to classes map with name "
          + loadClass.getName());
      log.info("Class " + userInput + " successfully loaded");
    } catch (ClassNotFoundException e) {
      log.error(e);
    }
  }
}
