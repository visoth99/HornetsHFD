package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;



public abstract class GameObject implements Drawable {
     protected Point2D location;
     protected Dimension dimension;
     public abstract float getX();
     public abstract float getY();
     public abstract void setXY(float i, float i1);

}
