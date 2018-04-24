package shaart.Task6;

public class Cat implements Animal {

  @Override
  public String play() {
    return "The cat is playing";
  }

  @Override
  public String voice() {
    return "The cat says: \"meow\"";
  }
}
