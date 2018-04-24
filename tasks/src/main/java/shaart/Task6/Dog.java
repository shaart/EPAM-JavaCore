package shaart.Task6;

public class Dog implements Animal {

  @Override
  public String play() {
    return "The dog is playing";
  }

  @Override
  public String voice() {
    return "The dog barks";
  }
}
