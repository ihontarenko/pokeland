package org.nulllab.nullengine.openworld.character;

import org.nulllab.nullengine.core.input.Input;
import org.nulllab.nullengine.openworld.object.GameObject;
import org.nulllab.nullengine.openworld.object.component.graphics.CharacterGraphics;
import org.nulllab.nullengine.openworld.object.component.handler.CharacterHandler;
import org.nulllab.nullengine.openworld.object.component.physics.CharacterPhysics;
import org.nulllab.nullengine.openworld.object.component.physics.Physics;

@SuppressWarnings("unused")
public class Character extends GameObject {

  public Character(Input input) {
    super(0, 0, 32, 48);

    this.setPriority(1 << 2);
    this.setMovable(true);

    // overwrite state handler for character
    this.setStateHandler(new CharacterHandler(this, input));
    // overwrite physics for character
    this.setPhysics(new CharacterPhysics(this));
    // overwrite graphics for character
    this.setGraphics(new CharacterGraphics(this));
    // set object velocity value
    this.setValue(Physics.VELOCITY, 2.0D);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void update(float nano) {
    getStateHandler().update(nano);
  }

}
