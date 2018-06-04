package shaart.task6;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j;

/**
 * Task 6. (15 points) Classloading <br> 1. Create a new abstract class (or interface) Animal with
 * methods: &quot;Play&quot;, &quot;Voice&quot;. <br> 2. Inherit Cat and Dog from the Animal class.
 * <br> 3. Compile the classes. <br> 4. Create your own ClassLoader by extension of the standard
 * ClassLoader. <br> 5. Load the classes Cat and Dog into an array or collection Animals by your own
 * class loader and run the methods &quot;Play&quot;, &quot;Voice&quot;. <br> 6. Output data to
 * console by log4j library (logger). Note: Methods should just print a text message.
 */
@Log4j
public class Main {

  public static void main(String[] args) {
    CustomClassLoader customClassLoader = CustomClassLoader.getInstance();
    List<Animal> animals = new ArrayList<>();
    try {
      animals.add((Animal) customClassLoader.findClass("shaart.task6.Cat").newInstance());
      animals.add((Animal) customClassLoader.findClass("shaart.task6.Dog").newInstance());
    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      log.error(e);
    }
    for (Animal animal : animals) {
      animal.play();
      animal.voice();
    }
  }
}