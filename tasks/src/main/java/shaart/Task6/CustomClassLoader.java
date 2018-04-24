package shaart.Task6;

import lombok.extern.log4j.Log4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Log4j
public class CustomClassLoader extends ClassLoader {

  private static CustomClassLoader instance;

  private CustomClassLoader() {
  }

  public CustomClassLoader(ClassLoader parent) {
    super(parent);
  }

  public static synchronized CustomClassLoader getInstance() {
    if (instance == null) {
      instance = new CustomClassLoader();
    }
    return instance;
  }

  @Override
  public Class<?> findClass(String name) throws ClassNotFoundException {
    byte[] bt = loadClassData(name);
    if (bt == null) {
      throw new ClassNotFoundException("Class " + name + " not found");
    }

    return defineClass(name, bt, 0, bt.length);
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    return super.loadClass(name);
  }

  private byte[] loadClassData(String className) {
    try (InputStream is = getClass()
        .getClassLoader()
        .getResourceAsStream(className.replace(".", "/") + ".class");
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream()
    ) {
      if (is == null) {
        log.error("InputStream was null");
        return null;
      }

      int dataByte;
      try {
        while ((dataByte = is.read()) != -1) {
          byteStream.write(dataByte);
        }
      } catch (IOException e) {
        log.error(e);
      }

      return byteStream.toByteArray();
    } catch (Exception e) {
      log.error("Can't load class data", e);
      return null;
    }
  }
}
