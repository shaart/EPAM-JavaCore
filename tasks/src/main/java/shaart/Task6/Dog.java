package shaart.Task6;

import lombok.extern.log4j.Log4j;

@Log4j
public class Dog implements Animal {

  @Override
  public void play() {
    log.info("The dog is playing");
  }

  @Override
  public void voice() {
    log.info("The dog barks");
  }
}
