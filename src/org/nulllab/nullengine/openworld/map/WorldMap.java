package org.nulllab.nullengine.openworld.map;

import org.nulllab.nullengine.core.common.Initializable;
import org.nulllab.nullengine.core.geometry.Bound2D;
import org.nulllab.nullengine.openworld.World;

public class WorldMap implements Initializable {

  private boolean initialized = false;
  private WorldMapData worldMapData;

  public WorldMap(String mapFile) {
    this.worldMapData = new WorldMapData(mapFile);
  }

  public Bound2D getBound() {
    return getWorldMapData().getBound();
  }

  public WorldMapData getWorldMapData() {
    return worldMapData;
  }

  public void buildWorldMap(World world) {
    WorldMapData data            = getWorldMapData();
    String       defaultSpriteID = data.getDefaultTile().getSpriteID();
    int          size            = data.getTileSize();
    int          width           = data.getWidth();
    int          height          = data.getHeight();

    for (int positionX = 0; positionX < width; positionX++) {
      for (int positionY = 0; positionY < height; positionY++) {

        final int x = data.getX(positionX);
        final int y = data.getY(positionY);

        Terrain defaultTerrain = createTerrainObject(x, y, size, world);
        defaultTerrain.setSpriteFromPackage(defaultSpriteID);

        if (data.hasTiles(positionX, positionY)) {
          data.getTiles(positionX, positionY).forEach(tile -> {
            Terrain terrain = createTerrainObject(x, y, size, world);
            terrain.setSpriteFromPackage(tile.getSpriteID());
            terrain.setSolid(tile.isSolid());
            terrain.setPriority(tile.getLayer());
          });
        }
      }
    }
  }

  private Terrain createTerrainObject(int x, int y, int tileSize, World world) {
    Terrain terrain = new Terrain(x, y, tileSize, tileSize);
    world.addGameObject(terrain);

    return terrain;
  }

  @Override
  public boolean isInitialized() {
    return initialized;
  }

  @Override
  public void initialize() {
    if (!isInitialized()) {
      WorldMapLoader loader = new WorldMapLoader(getWorldMapData().getMapFile());
      loader.toMapData(getWorldMapData());
      initialized = true;
    }
  }

  @Override
  public void reinitialize() {

  }
}
