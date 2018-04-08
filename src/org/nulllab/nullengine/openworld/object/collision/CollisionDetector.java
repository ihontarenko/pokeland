package org.nulllab.nullengine.openworld.object.collision;

import org.nulllab.nullengine.core.geometry.Bound2D;
import org.nulllab.nullengine.core.geometry.intersection.spatialhash.SpatialHash;
import org.nulllab.nullengine.openworld.ServiceLocator;
import org.nulllab.nullengine.openworld.object.GameObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollisionDetector {

  private SpatialHash<GameObject> spatialHash;

  public CollisionDetector() {
    this.spatialHash = ServiceLocator.getInstance().getWorld().getSpatialHash();
  }

  public List<GameObject> getNearestObjectsFor(GameObject object) {
    Bound2D          spatialBounds  = object.getSpatialBounds();
    List<GameObject> nearestObjects = new ArrayList<>(spatialHash.retrieve(spatialBounds));

    Collections.sort(nearestObjects);

    return nearestObjects;
  }

  public List<GameObject> getNearestSolidObjectsFor(GameObject object) {
    return getNearestObjectsFor(object).stream().filter(GameObject::isSolid).collect(Collectors.toList());
  }

  public boolean isCollidedWith(GameObject targetObject, GameObject solidObject) {
    return targetObject.getInnerBound().intersects(solidObject.getInnerBound());
  }

  public boolean isCollidedWith(GameObject targetObject, List<GameObject> solidObjects) {
    boolean isCollided = false;

    for (GameObject solidObject : solidObjects) {
      isCollided = isCollided || isCollidedWith(targetObject, solidObject);
    }

    return isCollided;
  }

  public boolean isCollidedWithNearest(GameObject object) {
    return isCollidedWith(object, getNearestObjectsFor(object));
  }

  public boolean isCollidedWithNearestSolid(GameObject object) {
    return isCollidedWith(object, getNearestSolidObjectsFor(object));
  }

}
