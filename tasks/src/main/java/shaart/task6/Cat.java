package shaart.task6;

import lombok.extern.log4j.Log4j;

@Log4j
public class Cat implements Animal {

  @Override
  public void play() {
    log.info("The cat is playing");
  }

  @Override
  public void voice() {
    log.info("The cat says: \"meow\"");
  }
}
