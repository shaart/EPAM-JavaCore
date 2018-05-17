package shaart.Task5;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.log4j.Log4j;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

@Log4j
public class CustomClassLoader extends ClassLoader {

  private static CustomClassLoader instance;
  /**
   * Cache with loaded classes
   */
  private static HashMap<String, Class> classesCache = new HashMap<>();
  private String pathToFolder;

  private CustomClassLoader() {
    super();
    this.pathToFolder = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
  }

  public CustomClassLoader(String pathToFolder, ClassLoader parent) {
    super(parent);
    this.pathToFolder = pathToFolder;
  }

  public CustomClassLoader(ClassLoader parent) {
    super(parent);
  }

  public <T> T getClassInstance(String name) {
    try {
      if (classesCache.containsKey(name)) {
        return (T) classesCache.get(name).newInstance();
      } else {
        Class<?> foundClass = findClass(name);
        return foundClass == null ? null : (T) foundClass.newInstance();
      }
    } catch (Exception e) {
      log.error(e);
      return null;
    }
  }

  public static synchronized CustomClassLoader getInstance() {
    if (instance == null) {
      instance = new CustomClassLoader();
    }
    return instance;
  }

  /**
   * Gets class from cache or file system
   *
   * @param className Path to <code>class</code> file without extension
   * @return Loaded class
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

    byte[] bt = loadClassData(classFilePath.toString());
    if (bt == null || bt.length == 0) {
      throw new ClassNotFoundException("Class " + className + " not found");
    }

    Class<?> loadedClass = defineClass(className, bt, 0, bt.length);
    classesCache.put(loadedClass.getName(), loadedClass);
    log.info("Class " + loadedClass.getName() + " added to cache");

    return loadedClass;
  }

  /**
   * Loads class from file system
   *
   * @param name Path to <code>class</code> file without extension
   * @return Loaded class
   */
  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    return super.loadClass(name);
  }

  /**
   * Loads class data from file
   *
   * @param classFileFullPath Full path to <code>class</code> file
   * @return Read class data
   */
  private byte[] loadClassData(String classFileFullPath) {
    try (InputStream is = new FileInputStream(classFileFullPath);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream()
    ) {
      int dataByte;

      while ((dataByte = is.read()) != -1) {
        byteStream.write(dataByte);
      }

      return byteStream.toByteArray();
    } catch (Exception e) {
      log.error("Can't load class data", e);
      return new byte[0];
    }
  }
}
