package org.nulllab.sorrowland.app.graphics;

import org.nulllab.nullengine.core.graphics.spritesheet.sheet.SpriteSheetPackage;
import org.nulllab.nullengine.core.graphics.spritesheet.sheet.SpriteSheetMapper;

public class WorldTilesSpritePackage extends SpriteSheetPackage {

  public WorldTilesSpritePackage() {
    super("map/tiles1.png");
  }

  @Override
  public SpriteSheetMapper[] getSpriteSheetMappers() {
    return new SpriteSheetMapper[] {
        new SpriteSheetMapper("sheet1", 32, 32, 384, 288, 2, 1),
        new SpriteSheetMapper("sheet2", 32, 32, 320, 288, 2, 1),
        new SpriteSheetMapper("sheet3", 32, 32, 0, 96, 2, 1),
    };
  }

}
