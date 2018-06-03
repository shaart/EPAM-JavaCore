package shaart.task5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.log4j.Log4j;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

@Log4j
public class RunnableClassLoader extends ClassLoader {

  private static final String CLASSES_FOLDER_NAME = "classes";
  private static final String CLASS_NOT_FOUND_FORMAT = "Class %s not found";
  private static final String CLASS_NOT_FOUND_BY_LOADER_CALL_PARENT =
      "Class %s not found by loader %s. Calling default class loader..";
  private static final String CLASS_ADDED_FORMAT = "Class %s added to cache";
  private static final String CLASS_NOT_IMPL_RUNNABLE =
      "Class loaded, but does not implement Runnable";

  private static final Class<Runnable> cachingClassesInterface = Runnable.class;

  /**
   * Cache with loaded classes
   */
  private static HashMap<String, Class> classesCache = new HashMap<>();
  private String pathToFolder;
  private static RunnableClassLoader instance;

  private RunnableClassLoader() {
    super();
    this.pathToFolder = Paths.get(CLASSES_FOLDER_NAME).toAbsolutePath().toString();
  }

  public RunnableClassLoader(String pathToFolder, ClassLoader parent) {
    super(parent);
    this.pathToFolder = pathToFolder;
  }

  public RunnableClassLoader(ClassLoader parent) {
    super(parent);
  }

  public static synchronized RunnableClassLoader getInstance() {
    if (instance == null) {
      instance = new RunnableClassLoader();
    }
    return instance;
  }

  /**
   * Gets class from cache or file system
   *
   * @param className Path to <code>class</code> file without extension
   * @return Loaded class
   * @throws ClassCastException If loaded class does not implement Runnable interface
   * @throws ClassNotFoundException If can not find and load class file
   */
  @Override
  public Class<?> findClass(String className) throws ClassNotFoundException {
    if (classesCache.containsKey(className)) {
      return classesCache.get(className);
    }

    Path classFilePath = Paths.get(pathToFolder)
        .resolve(Paths.get(className.replace(".", "/") + ".class"))
        .normalize()
        .toAbsolutePath();

    byte[] bt;
    try {
      bt = loadClassData(classFilePath.toString());
    } catch (Exception e) {
      throw new ClassNotFoundException(String.format(CLASS_NOT_FOUND_FORMAT, className));
    }

    if (bt == null || bt.length == 0) {
      throw new ClassNotFoundException(String.format(CLASS_NOT_FOUND_FORMAT, className));
    }

    Class<?> loadedClass = defineClass(className, bt, 0, bt.length);
    for (Class implInterface : loadedClass.getInterfaces()) {
      if (implInterface.equals(cachingClassesInterface)) {
        resolveClass(loadedClass);

        classesCache.put(loadedClass.getName(), loadedClass);
        log.trace(String.format(CLASS_ADDED_FORMAT, loadedClass.getName()));

        return loadedClass;
      }
    }
    log.trace(CLASS_NOT_IMPL_RUNNABLE);
    throw new ClassCastException(CLASS_NOT_IMPL_RUNNABLE);
  }

  /**
   * Loads class from file system
   *
   * @param name Path to <code>class</code> file without extension
   * @return Loaded class
   */
  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    try {
      return findClass(name);
    } catch (Exception e) {
      log.trace(String.format(CLASS_NOT_FOUND_BY_LOADER_CALL_PARENT, name, getClass().getName()));
      return super.loadClass(name);
    }
  }

  /**
   * Loads class data from file
   *
   * @param classFileFullPath Full path to <code>class</code> file
   * @return Read class data
   */
  private byte[] loadClassData(String classFileFullPath) throws FileNotFoundException {
    if (!Paths.get(classFileFullPath).toFile().exists()) {
      throw new FileNotFoundException();
    }
    try (InputStream is = new FileInputStream(classFileFullPath);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream()) {
      int dataByte;

      while ((dataByte = is.read()) != -1) {
        byteStream.write(dataByte);
      }

      return byteStream.toByteArray();
    } catch (Exception e) {
      return new byte[0];
    }
  }
}
