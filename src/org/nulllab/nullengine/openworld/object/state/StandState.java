package org.nulllab.nullengine.openworld.object.state;

import org.nulllab.nullengine.core.input.Input;
import org.nulllab.nullengine.openworld.character.Character;
import org.nulllab.nullengine.openworld.object.component.graphics.Graphics;

public class StandState extends ObjectState<Character> {

  private int[] controlKeys;

  @Override
  public void entryAction(Character object, Input input) {
    controlKeys = new int[]{Input.LEFT, Input.RIGHT, Input.UP, Input.DOWN};

    Graphics graphics = object.getGraphics();

    if (graphics.getSprite() == null) {
      graphics.setSprite(graphics.getObjectSprites().getDefaultSprite());
    }
  }

  @Override
  public ObjectState handle(Character object, Input input) {
    ObjectState state = null;

    if (input.isPressed(controlKeys)) {
      state = new MoveState();
    }

    return state;
  }
}
