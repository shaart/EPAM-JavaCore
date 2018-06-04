package shaart.task5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
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

  enum Menu {SHOW_LOADED, ADD_OR_UPDATE_CLASS, INVOKE_CLASS_METHOD, EXIT}

  private static final String FUNCTIONALITY_JAR_NAME_WITH_EXT = "func.jar";
  private static final Scanner scanner = new Scanner(System.in);
  private static final ClassLoader runnableClassLoader = RunnableClassLoader.getInstance();
  private static final Map<String, Class> classes = new HashMap<>();
  private static final String TEMP_FOLDER_PATH = "temp";

  public static void main(String[] args) {
    Menu[] menuValues = Menu.values();

    String userInput;
    int userOption;
    final String menu = "\n---------\n Menu\n" +
        "---------\n" +
        Menu.SHOW_LOADED.ordinal() + ". Show classes loaded by RunnableClassLoader \n" +
        Menu.ADD_OR_UPDATE_CLASS.ordinal() + ". Add/Update class\n" +
        Menu.INVOKE_CLASS_METHOD.ordinal() + ". Invoke class methods\n" +
        "---------\n" +
        Menu.EXIT.ordinal() + ". Exit\n";
    boolean working = true;

    while (working) {
      log.info(menu);
      log.info("Enter menu option:");
      userInput = scanner.nextLine();
      log.debug(String.format("Got option: %s", userInput));
      try {
        userOption = Integer.parseInt(userInput);
      } catch (NumberFormatException e) {
        log.error("Incorrect input");
        continue;
      }

      switch (menuValues[userOption]) {
        case SHOW_LOADED:
          showLoadedClasses();
          break;
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

  private static void createEmptyJar(String jarName) throws IOException {
    Manifest manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
    //noinspection EmptyTryBlock
    try (JarOutputStream ignored =
        new JarOutputStream(new FileOutputStream(jarName), manifest)) {/*NOSONAR*/
    }
  }

  private static void invokeClassMethodsInterface() {
    log.info("Input class name: ");
    String userInput = scanner.nextLine();
    log.info(String.format("Got input: %s", userInput));
    try {
      log.info("Getting specified class...");
      Class<?> loadClass = classes.get(userInput);
      if (loadClass == null) {
        log.info("Class not loaded.");
        return;
      }
      log.info("Creating instance of specified class...");
      Runnable instance = (Runnable) loadClass.newInstance();
      log.info("Calling instance's run()...\n");

      instance.run();

      log.info("\nInstance's run() ended.");
    } catch (InstantiationException | IllegalAccessException e) {
      log.error(e);
    }
  }

  private static void loadClassInterface() {
    log.info("Input class name: ");
    String userInput = scanner.nextLine();
    log.info("Got input: " + userInput);
    try {
      Class<?> loadedClass = runnableClassLoader.loadClass(userInput);
      classes.put(loadedClass.getName(), loadedClass);

      int indexOfDot = userInput.lastIndexOf('.');
      String packageName;
      packageName = indexOfDot == -1 ? "" : userInput.substring(0, indexOfDot);

      updateFunctionality(loadedClass, packageName);
      log.trace(String.format("%s added to classes map with name %s",
          loadedClass.getCanonicalName(), loadedClass.getName()));
      log.info(String.format("Class %s successfully loaded", userInput));
    } catch (Error | Exception e) {
      log.error(e);
    }
  }

  private static File saveClass(Class<?> savingClass, String packageName) throws IOException {
    String fileName = savingClass.getSimpleName();

    Path tempDir = Paths.get(TEMP_FOLDER_PATH, packageName.split("\\."));
    Files.createDirectories(tempDir);
    File filePath = new File(tempDir.toFile(), fileName + ".class");

    try (FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(savingClass);
      oos.flush();
    }

    return filePath;
  }

  private static void saveFileToJar(String jarName, String packageDir, File file)
      throws IOException {
    String fileName = file.getName();
    File jarFile = new File(jarName);
    File tempJarFile = new File(jarName + ".tmp");

    boolean jarUpdated = false;
    Path jarPath = Paths.get(jarName);
    if (!jarPath.toFile().exists()) {
      createEmptyJar(jarName);
    }
    try (JarFile jar = new JarFile(jarFile)) {
      log.trace(FUNCTIONALITY_JAR_NAME_WITH_EXT + " opened.");
      try (JarOutputStream tempJar =
          new JarOutputStream(new FileOutputStream(tempJarFile))) {
        byte[] buffer = new byte[1024];
        int bytesRead;

        try (FileInputStream classFile = new FileInputStream(file)) {
          String packagePath = packageDir.replace('.', '/');
          JarEntry entry = new JarEntry(packagePath + "/" + fileName);
          tempJar.putNextEntry(entry);

          while ((bytesRead = classFile.read(buffer)) != -1) {
            tempJar.write(buffer, 0, bytesRead);
          }
          log.trace(entry.getName() + " added.");
        }

        // Copy jar entries to tempJar
        for (Enumeration entries = jar.entries(); entries.hasMoreElements(); ) {
          JarEntry entry = (JarEntry) entries.nextElement();
          if (entry.getName().equals(fileName)) {
            continue;
          }

          // If the entry has not been added already, add it.
          InputStream entryStream = jar.getInputStream(entry);
          tempJar.putNextEntry(entry);

          while ((bytesRead = entryStream.read(buffer)) != -1) {
            tempJar.write(buffer, 0, bytesRead);
          }
        }

        jarUpdated = true;
      } catch (Exception e) {
        log.error(e);
      }
    }
    if (jarUpdated) {
      jarFile.delete();
      tempJarFile.renameTo(jarFile);
      log.trace(jarName + " updated.");
    } else {
      tempJarFile.delete();
    }
  }

  private static void showLoadedClasses() {
    if (classes.size() == 0) {
      log.info("No classes loaded");
    } else {
      log.info("Loaded classes:");
      for (Entry<String, Class> classEntry : classes.entrySet()) {
        log.info("- Name: " + classEntry.getKey());
      }
    }
  }

  private static void updateFunctionality(Class<?> addingClass, String packageName)
      throws IOException {
    File tempClassFileName = saveClass(addingClass, packageName);

    saveFileToJar(FUNCTIONALITY_JAR_NAME_WITH_EXT, packageName, tempClassFileName);

    boolean deleted = Files.deleteIfExists(Paths.get(tempClassFileName.toString()));
    log.trace(String.format("Temp %s file was %s", tempClassFileName,
        deleted ? "removed" : "NOT removed"));
  }
}
