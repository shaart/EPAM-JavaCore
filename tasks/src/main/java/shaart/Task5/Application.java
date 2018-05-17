package shaart.Task5;

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

  enum Menu {AddNewClass, UpdateExistingClass, InvokeClassMethods, Exit}

  private static Scanner scanner = new Scanner(System.in);
  private static CustomClassLoader customClassLoader = CustomClassLoader.getInstance();

  public static void main(String[] args) {
    Menu[] menuValues = Menu.values();

    String userInput;
    int userOption;
    final String menu = "Menu\n" +
        "---------\n" +
        Menu.AddNewClass.ordinal() + ". Add new class\n" +
        Menu.UpdateExistingClass.ordinal() + ". Update existing class\n" +
        Menu.InvokeClassMethods.ordinal() + ". Invoke class methods\n" +
        "---------\n" +
        Menu.Exit.ordinal() + ". Exit\n";
    boolean working = true;
    System.out.println(menu);

    while (working) {
      System.out.print("> ");
      userInput = scanner.nextLine();
      try {
        userOption = Integer.parseInt(userInput);
      } catch (NumberFormatException e) {
        System.err.println("Incorrect input");
        continue;
      }

      switch (menuValues[userOption]) {
        case AddNewClass:
          loadClassInterface();
          break;
        case UpdateExistingClass:
          updateClassInterface();
          break;
        case InvokeClassMethods:
          invokeClassMethodsInterface();
          break;
        case Exit:
          working = false;
          break;
        default:
          System.err.println("Unknown menu option");
          break;
      }
    }
  }

  private static void invokeClassMethodsInterface() {

  }

  private static void loadClassInterface() {
    System.out.print("Input class name: ");
    String userInput = scanner.nextLine();
    try {
      customClassLoader.findClass(userInput);
      log.info("Class " + userInput + " successfully loaded");
    } catch (ClassNotFoundException e) {
      log.error(e);
    }
  }

  private static void updateClassInterface() {

  }
}
