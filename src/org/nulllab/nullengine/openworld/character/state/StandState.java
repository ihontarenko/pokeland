package org.nulllab.nullengine.openworld.character.state;

import org.nulllab.nullengine.core.input.Input;
import org.nulllab.nullengine.openworld.character.Character;
import org.nulllab.nullengine.openworld.state.ObjectState;

public class StandState extends ObjectState<Character> {

  @Override
  public void entryAction(Character object) {
    object.setSprite(object.getSpritePackage().getStandSouth());
  }

  @Override
  public ObjectState handle(Character object, Input input) {
    ObjectState state = null;

    if (input.isPressed(new int[]{Input.LEFT, Input.RIGHT, Input.UP, Input.DOWN})) {
      state = new MoveState();
    }

    return state;
  }
}